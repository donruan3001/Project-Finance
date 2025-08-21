package finance.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.dto.analytics.AnalyticsDTO;
import finance.dto.analytics.MonthlyComparisonDTO;
import finance.repository.RepositoryTransactions;
import finance.repository.RepositoryUser;

@Service
public class ServiceAnalytics {
    
    @Autowired
    private RepositoryUser repositoryUser;
    
    @Autowired
    private RepositoryTransactions repositoryTransactions;
    
    public AnalyticsDTO getAnalytics(Long userId, LocalDate startDate, LocalDate endDate) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);
        
        List<Transaction> transactions = repositoryTransactions.findAll()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId))
            .filter(t -> t.getCreated().isAfter(start) && t.getCreated().isBefore(end))
            .toList();
        
        // Expenses by category
        Map<String, BigDecimal> expensesByCategory = transactions.stream()
            .filter(t -> t.getType() == TypeTransaction.EXPENSE)
            .collect(Collectors.groupingBy(
                t -> t.getCategory() != null ? t.getCategory().name() : "Outros",
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
            ));
        
        // Income by category
        Map<String, BigDecimal> incomeByCategory = transactions.stream()
            .filter(t -> t.getType() == TypeTransaction.INCOME)
            .collect(Collectors.groupingBy(
                t -> t.getCategory() != null ? t.getCategory().name() : "Outros",
                Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)
            ));
        
        // Calculate totals
        BigDecimal totalIncome = incomeByCategory.values().stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalExpenses = expensesByCategory.values().stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Calculate savings rate
        BigDecimal savings = totalIncome.subtract(totalExpenses);
        BigDecimal savingsRate = totalIncome.compareTo(BigDecimal.ZERO) > 0 
            ? savings.divide(totalIncome, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))
            : BigDecimal.ZERO;
        
        // Find top categories
        String topExpenseCategory = expensesByCategory.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Nenhuma");
        
        String topIncomeCategory = incomeByCategory.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Nenhuma");
        
        // Monthly comparison (simplified)
        List<MonthlyComparisonDTO> monthlyComparison = generateMonthlyComparison(transactions);
        
        return new AnalyticsDTO(
            expensesByCategory,
            incomeByCategory,
            monthlyComparison,
            List.of(), // categoryTrends - to be implemented
            totalIncome, // averageMonthlyIncome - simplified
            totalExpenses, // averageMonthlyExpenses - simplified
            savingsRate,
            topExpenseCategory,
            topIncomeCategory,
            List.of() // budgetPerformance - to be implemented
        );
    }
    
    private List<MonthlyComparisonDTO> generateMonthlyComparison(List<Transaction> transactions) {
        Map<String, List<Transaction>> transactionsByMonth = transactions.stream()
            .collect(Collectors.groupingBy(
                t -> t.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM"))
            ));
        
        return transactionsByMonth.entrySet().stream()
            .map(entry -> {
                String month = entry.getKey();
                List<Transaction> monthTransactions = entry.getValue();
                
                BigDecimal income = monthTransactions.stream()
                    .filter(t -> t.getType() == TypeTransaction.INCOME)
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                BigDecimal expenses = monthTransactions.stream()
                    .filter(t -> t.getType() == TypeTransaction.EXPENSE)
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
                
                BigDecimal savings = income.subtract(expenses);
                Double savingsRate = income.compareTo(BigDecimal.ZERO) > 0 
                    ? savings.divide(income, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).doubleValue()
                    : 0.0;
                
                return new MonthlyComparisonDTO(month, income, expenses, savings, savingsRate);
            })
            .sorted(Comparator.comparing(MonthlyComparisonDTO::month))
            .toList();
    }
}