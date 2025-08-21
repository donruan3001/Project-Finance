package finance.dto.user;

import finance.domain.user.RoleUser;

public record UserResponseDTO (

    Long id,
    String name,
    String email,
    RoleUser role

){
}
