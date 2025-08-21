package finance.dto.analytics;

import java.math.BigDecimal;

public record MonthlyValueDTO(
    String month,
    BigDecimal amount
) {}