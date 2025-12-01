package finance.dto.accounts;

import finance.domain.acounts.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateDTO(
        @NotNull(message = "account.userId.required")
        Long userId,


        @NotBlank(message = "account.name.required")
        String name,

        @NotNull(message = "account.type.required")
        AccountType type,

        @NotNull(message = "account.balance.required")
        @DecimalMin(value = "0.00", inclusive = true, message = "account.balance.negative")
        BigDecimal balance


) {
}
