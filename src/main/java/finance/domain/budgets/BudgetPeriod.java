package finance.domain.budgets;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BudgetPeriod {
    WEEKLY,
    MONTHLY,
    QUARTERLY,
    YEARLY,
    CUSTOM;
    
    @JsonCreator
    public static BudgetPeriod fromString(String value) {
        return BudgetPeriod.valueOf(value.toUpperCase());
    }
}