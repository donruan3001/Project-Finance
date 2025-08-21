package finance.dto.goals;

import finance.domain.goals.GoalPriority;
import finance.domain.goals.GoalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GoalResponseDTO(
    Long id,
    Long userId,
    String name,
    String description,
    BigDecimal targetAmount,
    BigDecimal currentAmount,
    Double progressPercentage,
    LocalDate targetDate,
    GoalStatus status,
    GoalPriority priority,
    String icon,
    String color,
    Long daysRemaining,
    LocalDateTime createdAt
) {}