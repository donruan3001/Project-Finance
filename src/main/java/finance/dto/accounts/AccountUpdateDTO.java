package finance.dto.accounts;

import finance.domain.acounts.AccountType;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record AccountUpdateDTO(
        String name,
       AccountType type,
       BigDecimal balance,
       Long bankId ) {
}
