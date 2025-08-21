package finance.dto.analytics;

import java.math.BigDecimal;

public record MonthlyComparisonDTO(
    String month,
    BigDecimal income,
    BigDecimal expenses,
    BigDecimal savings,
    Double savingsRate
) {}