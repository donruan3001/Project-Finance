package finance.dto.dashboard;

import finance.domain.transactions.TypeTransaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RecentTransactionDTO(
    Long id,
    String name,
    String category,
    TypeTransaction type,
    BigDecimal amount,
    String accountName,
    LocalDateTime created
) {}