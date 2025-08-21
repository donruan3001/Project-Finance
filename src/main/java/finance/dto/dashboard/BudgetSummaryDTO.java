package finance.dto.dashboard;

import java.math.BigDecimal;

public record BudgetSummaryDTO(
    Long id,
    String name,
    String categoryName,
    BigDecimal amount,
    BigDecimal spent,
    Double progressPercentage,
    String status
) {}