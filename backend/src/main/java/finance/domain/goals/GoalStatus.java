package finance.domain.goals;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GoalStatus {
    ACTIVE,
    COMPLETED,
    PAUSED,
    CANCELLED;
    
    @JsonCreator
    public static GoalStatus fromString(String value) {
        return GoalStatus.valueOf(value.toUpperCase());
    }
}