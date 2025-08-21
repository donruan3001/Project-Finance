package finance.dto.analytics;

import java.math.BigDecimal;
import java.util.List;

public record CategoryTrendDTO(
    String categoryName,
    List<MonthlyValueDTO> monthlyValues,
    BigDecimal averageAmount,
    String trend
) {}