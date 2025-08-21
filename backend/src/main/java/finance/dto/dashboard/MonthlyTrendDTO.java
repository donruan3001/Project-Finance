package finance.dto.dashboard;

import java.math.BigDecimal;

public record MonthlyTrendDTO(
    String month,
    BigDecimal income,
    BigDecimal expenses,
    BigDecimal balance
) {}