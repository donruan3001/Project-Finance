package finance.dto.dashboard;

import finance.domain.acounts.AccountType;

import java.math.BigDecimal;

public record AccountSummaryDTO(
    Long id,
    String name,
    AccountType type,
    BigDecimal balance,
    String bankName
) {}