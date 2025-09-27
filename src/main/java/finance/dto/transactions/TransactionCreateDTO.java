package finance.dto.transactions;

import finance.domain.transactions.CategoryTransactions;
import finance.domain.transactions.TypeTransaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransactionCreateDTO(
        @NotNull
        Long accountId,
        @NotNull
         String name,
         CategoryTransactions category,
         @NotNull
         TypeTransaction type,
         @NotNull
         @Positive
         BigDecimal amount
) {
}
