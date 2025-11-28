package finance.dto.accounts;

import finance.domain.acounts.AccountType;

import java.math.BigDecimal;

public record AccountUpdateDTO(
        String name,
        String bank,  // Opcional - pode ser null
        AccountType type,
        BigDecimal balance) {
}
