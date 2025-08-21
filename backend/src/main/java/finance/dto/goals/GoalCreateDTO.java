package finance.dto.goals;

import finance.domain.goals.GoalPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalCreateDTO(
    @NotNull Long userId,
    @NotBlank String name,
    String description,
    @NotNull @Positive BigDecimal targetAmount,
    @NotNull LocalDate targetDate,
    @NotNull GoalPriority priority,
    String icon,
    String color
) {}