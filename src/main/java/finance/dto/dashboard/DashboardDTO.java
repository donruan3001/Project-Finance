package finance.dto.dashboard;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record DashboardDTO(
    BigDecimal totalBalance,
    BigDecimal monthlyIncome,
    BigDecimal monthlyExpenses,
    BigDecimal monthlyBudget,
    Double budgetUsagePercentage,
    List<AccountSummaryDTO> accounts,
    List<RecentTransactionDTO> recentTransactions,
    List<BudgetSummaryDTO> budgets,
    List<GoalSummaryDTO> goals,
    Map<String, BigDecimal> expensesByCategory,
    Map<String, BigDecimal> incomeByCategory,
    List<MonthlyTrendDTO> monthlyTrends
) {}