package finance.dto.accounts;

import finance.domain.acounts.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateDTO(
        @NotNull
        Long userId,

        @NotNull
        Long bankId,

        @NotBlank
        String name,


        AccountType type,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = true, message = "balance não pode ser negativo")
        BigDecimal balance


) {
}
