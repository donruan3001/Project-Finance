package finance.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import finance.domain.acounts.Account;
import finance.domain.notifications.NotificationType;
import finance.domain.transactions.Transaction;
import finance.domain.transactions.TypeTransaction;
import finance.domain.user.User;
import finance.dto.transactions.TransactionCreateDTO;
import finance.dto.transactions.TransactionResponseDTO;
import finance.repository.RepositoryAccount;
import finance.repository.RepositoryBudget;
import finance.repository.RepositoryTransactions;
import finance.repository.RepositoryUser;
import finance.validators.transactions.Validator;
import jakarta.transaction.Transactional;

@Service
public class ServiceTransactions {

    @Autowired
    private RepositoryUser repositoryUser;
    @Autowired
    private RepositoryTransactions repositoryTransactions;
    @Autowired
    private RepositoryAccount repositoryAccount;
    @Autowired
    private List<Validator> validators;
    @Autowired
    private RepositoryBudget repositoryBudget;
    @Autowired
    private ServiceNotification serviceNotification;

    @Transactional
    public void createTransaction(TransactionCreateDTO data) {
        User user = repositoryUser.findById(data.userId()).
                orElseThrow(() -> new IllegalArgumentException("Usuário" + data.userId() + " não encontrado"));
        Account account = repositoryAccount.findById(data.accountId()).
                orElseThrow(() -> new IllegalArgumentException("Conta " + data.accountId() + " não encontrada"));

        validators.forEach(validator -> validator.validate(data));

        //cria o um objeto Transaction
        var transaction = new Transaction(user, account, data.category(), data.name().trim(), data.type(), data.amount());
       // Atualiza o saldo da conta
        if (data.type().equals(TypeTransaction.EXPENSE)){
            account.setBalance(account.getBalance().subtract(data.amount()));
            repositoryAccount.save(account);
        }
        // Se for uma transação de entrada, adiciona o valor ao saldo da conta
        if (data.type().equals(TypeTransaction.INCOME)) {
            account.setBalance(account.getBalance().add(data.amount()));
            repositoryAccount.save(account);
        }

        repositoryTransactions.save(transaction);
        
        // Update budget if applicable
        if (data.type().equals(TypeTransaction.EXPENSE)) {
            updateBudgetSpent(user, data.amount(), LocalDate.now());
        }
        
        // Create notification for large transactions
        if (data.amount().compareTo(java.math.BigDecimal.valueOf(1000)) > 0) {
            serviceNotification.createNotification(
                user.getId(),
                "Transação de alto valor",
                String.format("Transação de %s no valor de R$ %.2f foi registrada", 
                    data.type() == TypeTransaction.INCOME ? "receita" : "despesa", 
                    data.amount()),
                NotificationType.TRANSACTION_ALERT,
                "/transactions"
            );
        }
    }
    
    public Page<TransactionResponseDTO> getTransactionsByUser(Long userId, Pageable pageable) {
        var user = repositoryUser.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        Page<Transaction> transactions = repositoryTransactions.findAll(pageable);
        
        // Filter transactions by user ID and map to DTOs
        List<TransactionResponseDTO> filteredTransactions = transactions.getContent()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId))
            .map(this::mapToResponseDTO)
            .toList();
        
        return new PageImpl<>(filteredTransactions, pageable, filteredTransactions.size());
    }
    
    public List<TransactionResponseDTO> getRecentTransactions(Long userId, int limit) {
        return repositoryTransactions.findAll()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId))
            .sorted((t1, t2) -> t2.getCreated().compareTo(t1.getCreated()))
            .limit(limit)
            .map(this::mapToResponseDTO)
            .toList();
    }
    
    @Transactional
    public void deleteTransaction(Long transactionId, Long userId) {
        var transaction = repositoryTransactions.findById(transactionId)
            .orElseThrow(() -> new IllegalArgumentException("Transação não encontrada"));
        
        if (!transaction.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Acesso negado: transação não pertence ao usuário");
        }
        
        // Reverse account balance
        var account = transaction.getAccount();
        if (transaction.getType().equals(TypeTransaction.EXPENSE)) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        } else {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        }
        repositoryAccount.save(account);
        
        repositoryTransactions.delete(transaction);
    }
    
    private void updateBudgetSpent(User user, java.math.BigDecimal amount, LocalDate date) {
        var budgets = repositoryBudget.findActiveBudgetsForUserAndDate(user, date);
        
        budgets.forEach(budget -> {
            budget.setSpent(budget.getSpent().add(amount));
            repositoryBudget.save(budget);
            
            // Check if budget is exceeded
            if (budget.getSpent().compareTo(budget.getAmount()) > 0) {
                serviceNotification.createNotification(
                    user.getId(),
                    "Orçamento excedido",
                    String.format("O orçamento '%s' foi excedido em R$ %.2f", 
                        budget.getName(), 
                        budget.getSpent().subtract(budget.getAmount())),
                    NotificationType.BUDGET_ALERT,
                    "/budgets"
                );
            }
        });
    }
    
    private TransactionResponseDTO mapToResponseDTO(Transaction transaction) {
        return new TransactionResponseDTO(
            transaction.getId(),
            transaction.getUser().getId(),
            transaction.getAccount().getId(),
            transaction.getCategory(),
            transaction.getName(),
            transaction.getAmount(),
            transaction.getCreated(),
            transaction.getUpdated()
        );
    }}


