package finance.services;

import finance.domain.acounts.Account;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.dto.dashboard.*;
import finance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceDashboard {
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private RepositoryAccount repositoryAccount;
    
    @Autowired
    private RepositoryTransactions repositoryTransactions;
    
    @Autowired
    private RepositoryBudget repositoryBudget;
    
    @Autowired
    private RepositoryGoal repositoryGoal;
    
    public DashboardDTO getDashboard(Long userId) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        // Get all user accounts
        List<Account> accounts = repositoryAccount.findAll()
            .stream()
            .filter(account -> account.getUser().getId().equals(userId))
            .toList();
        
        // Calculate total balance
        BigDecimal totalBalance = accounts.stream()
            .map(Account::getBalance)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Get current month transactions
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59);
        
        List<Transaction> monthlyTransactions = repositoryTransactions.findAll()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId))
            .filter(t -> t.getCreated().isAfter(startOfMonth) && t.getCreated().isBefore(endOfMonth))
            .toList();
        
        BigDecimal monthlyIncome = monthlyTransactions.stream()
            .filter(t -> t.getType() == TypeTransaction.INCOME)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal monthlyExpenses = monthlyTransactions.stream()
            .filter(t -> t.getType() == TypeTransaction.EXPENSE)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Get recent transactions
        List<RecentTransactionDTO> recentTransactions = repositoryTransactions.findAll()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId))
            .sorted((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()))
            .limit(10)
            .map(this::mapToRecentTransactionDTO)
            .toList();
        
        // Get account summaries
        List<AccountSummaryDTO> accountSummaries = accounts.stream()
            .map(this::mapToAccountSummaryDTO)
            .toList();
        
        // Get expenses by category
        Map<String, BigDecimal> expensesByCategory = monthlyTransactions.stream()
            .filter(t -> t.getType() == TypeTransaction.EXPENSE)
            .collect(Collectors.groupingBy(
                t -> t.getCategory() != null ? t.getCategory().name() : "Outros",
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
            ));
        
        // Get income by category
        Map<String, BigDecimal> incomeByCategory = monthlyTransactions.stream()
            .filter(t -> t.getType() == TypeTransaction.INCOME)
            .collect(Collectors.groupingBy(
                t -> t.getCategory() != null ? t.getCategory().name() : "Outros",
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
            ));
        
        return new DashboardDTO(
            totalBalance,
            monthlyIncome,
            monthlyExpenses,
            BigDecimal.ZERO, // monthlyBudget - to be calculated from budgets
            0.0, // budgetUsagePercentage
            accountSummaries,
            recentTransactions,
            List.of(), // budgets - to be implemented
            List.of(), // goals - to be implemented
            expensesByCategory,
            incomeByCategory,
            List.of() // monthlyTrends - to be implemented
        );
    }
    
    private RecentTransactionDTO mapToRecentTransactionDTO(Transaction transaction) {
        return new RecentTransactionDTO(
            transaction.getId(),
            transaction.getName(),
            transaction.getCategory() != null ? transaction.getCategory().name() : "Outros",
            transaction.getType(),
            transaction.getAmount(),
            transaction.getAccount().getName(),
            transaction.getCreated()
        );
    }
    
    private AccountSummaryDTO mapToAccountSummaryDTO(Account account) {
        return new AccountSummaryDTO(
            account.getId(),
            account.getName(),
            account.getType(),
            account.getBalance(),
            account.getBank() != null ? account.getBank().getName() : "Sem banco"
        );
    }
}