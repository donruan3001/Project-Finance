package finance.domain.reports;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportType {
    INCOME_EXPENSE,
    BUDGET_ANALYSIS,
    GOAL_PROGRESS,
    CATEGORY_BREAKDOWN,
    MONTHLY_SUMMARY,
    YEARLY_SUMMARY,
    CUSTOM;
    
    @JsonCreator
    public static ReportType fromString(String value) {
        return ReportType.valueOf(value.toUpperCase());
    }
}