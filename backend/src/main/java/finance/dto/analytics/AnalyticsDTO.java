package finance.dto.analytics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record AnalyticsDTO(
    Map<String, BigDecimal> expensesByCategory,
    Map<String, BigDecimal> incomeByCategory,
    List<MonthlyComparisonDTO> monthlyComparison,
    List<CategoryTrendDTO> categoryTrends,
    BigDecimal averageMonthlyIncome,
    BigDecimal averageMonthlyExpenses,
    BigDecimal savingsRate,
    String topExpenseCategory,
    String topIncomeCategory,
    List<BudgetPerformanceDTO> budgetPerformance
) {}