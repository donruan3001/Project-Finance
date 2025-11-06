package finance.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
public enum RoleUser {
    USER,
    ADMIN;


    @JsonCreator
    public static RoleUser fromString(String value) {
        return RoleUser.valueOf(value.toUpperCase());
    }
}