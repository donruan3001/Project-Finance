package finance.domain.transactions;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypeTransaction {
EXPENSE,
INCOME;
    @JsonCreator
    public static TypeTransaction fromString(String value) {
        return TypeTransaction.valueOf(value.toUpperCase());
    }
}
