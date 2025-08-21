package finance.dto.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import finance.domain.transactions.CategoryTransactions;


public record TransactionResponseDTO(
    Long id,
    Long userId,
    Long accountId,
    CategoryTransactions category,
    String name,
    BigDecimal amount,
    LocalDateTime created,
    LocalDateTime updated
) {
}
