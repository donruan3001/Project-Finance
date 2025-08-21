package finance.dto.categories;

import finance.domain.categories.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateDTO(
    @NotBlank String name,
    String description,
    String icon,
    String color,
    @NotNull CategoryType type
) {}