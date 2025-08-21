package finance.dto.budgets;

import finance.domain.budgets.BudgetPeriod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BudgetCreateDTO(
    @NotNull Long userId,
    @NotNull Long categoryId,
    @NotBlank String name,
    @NotNull @Positive BigDecimal amount,
    @NotNull LocalDate startDate,
    @NotNull LocalDate endDate,
    @NotNull BudgetPeriod period
) {}