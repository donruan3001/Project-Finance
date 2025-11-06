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

}
