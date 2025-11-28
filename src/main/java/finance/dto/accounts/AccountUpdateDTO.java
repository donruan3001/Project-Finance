package finance.dto.accounts;

import java.math.BigDecimal;

import finance.domain.acounts.AccountType;

public record AccountUpdateDTO(
        String name,
        String bank,  // Opcional - pode ser null
        AccountType type,
        BigDecimal balance) {
}
