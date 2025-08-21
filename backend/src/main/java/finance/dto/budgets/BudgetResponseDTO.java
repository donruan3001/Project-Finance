package finance.dto.budgets;

import finance.domain.budgets.BudgetPeriod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BudgetResponseDTO(
    Long id,
    Long userId,
    Long categoryId,
    String categoryName,
    String name,
    BigDecimal amount,
    BigDecimal spent,
    BigDecimal remaining,
    Double progressPercentage,
    LocalDate startDate,
    LocalDate endDate,
    BudgetPeriod period,
    Boolean isActive,
    LocalDateTime createdAt
) {}