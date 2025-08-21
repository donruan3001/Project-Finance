package finance.dto.categories;

import finance.domain.categories.CategoryType;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
    Long id,
    String name,
    String description,
    String icon,
    String color,
    CategoryType type,
    Boolean isActive,
    LocalDateTime createdAt
) {}