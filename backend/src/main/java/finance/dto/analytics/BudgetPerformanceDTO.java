package finance.dto.analytics;

import java.math.BigDecimal;

public record BudgetPerformanceDTO(
    String budgetName,
    String categoryName,
    BigDecimal budgetAmount,
    BigDecimal actualAmount,
    Double variance,
    String performance
) {}