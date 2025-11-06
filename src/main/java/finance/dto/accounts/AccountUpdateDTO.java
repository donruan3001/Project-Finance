package finance.dto.accounts;

import finance.domain.acounts.AccountType;

import java.math.BigDecimal;

public record AccountUpdateDTO(
        String name,
       AccountType type,
       BigDecimal balance) {
}
