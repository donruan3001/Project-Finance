package finance.domain.goals;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GoalPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT;
    
    @JsonCreator
    public static GoalPriority fromString(String value) {
        return GoalPriority.valueOf(value.toUpperCase());
    }
}