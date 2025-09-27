package finance.dto.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import finance.domain.transactions.CategoryTransactions;
import finance.domain.transactions.Transaction;


public record TransactionResponseDTO(
        Long transactionId, Long id,
        Long accountId,
        CategoryTransactions category,
        String name,
        BigDecimal amount,
        LocalDateTime created,
        LocalDateTime updated
) {
    public TransactionResponseDTO(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getAccount().getUser().getId(),  // <- pega o dono
                transaction.getAccount().getId(),
                transaction.getCategory(),
                transaction.getName(),
                transaction.getAmount(),
                transaction.getCreated(),
                transaction.getUpdated()
        );
    }
}
