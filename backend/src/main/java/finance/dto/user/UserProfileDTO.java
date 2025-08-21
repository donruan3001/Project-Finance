package finance.dto.user;

import finance.domain.user.RoleUser;

public record UserProfileDTO(
    Long id,
    String name,
    String email,
    RoleUser role
) {}