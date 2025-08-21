package finance.dto.dashboard;

import finance.domain.goals.GoalPriority;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalSummaryDTO(
    Long id,
    String name,
    BigDecimal targetAmount,
    BigDecimal currentAmount,
    Double progressPercentage,
    LocalDate targetDate,
    GoalPriority priority,
    String icon,
    String color
) {}