package finance.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import finance.domain.transactions.CategoryTransactions;

public enum RoleUser {
    USER,
    ADMIN;


    @JsonCreator
    public static RoleUser fromString(String value) {
        return RoleUser.valueOf(value.toUpperCase());
    }
}