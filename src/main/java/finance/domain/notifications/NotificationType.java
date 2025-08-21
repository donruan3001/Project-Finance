package finance.domain.notifications;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum NotificationType {
    BUDGET_ALERT,
    GOAL_REMINDER,
    TRANSACTION_ALERT,
    BILL_REMINDER,
    SECURITY_ALERT,
    SYSTEM_UPDATE;
    
    @JsonCreator
    public static NotificationType fromString(String value) {
        return NotificationType.valueOf(value.toUpperCase());
    }
}