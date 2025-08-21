package finance.domain.categories;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoryType {
    INCOME,
    EXPENSE,
    BOTH;
    
    @JsonCreator
    public static CategoryType fromString(String value) {
        return CategoryType.valueOf(value.toUpperCase());
    }
}