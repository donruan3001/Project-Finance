import React from 'react';
import { 
  BanknotesIcon, 
  ArrowTrendingUpIcon, 
  ArrowTrendingDownIcon,
  CreditCardIcon 
} from '@heroicons/react/24/outline';
import { StatsCard } from '../components/dashboard/StatsCard';
import { RecentTransactions } from '../components/dashboard/RecentTransactions';
import { BudgetProgress } from '../components/dashboard/BudgetProgress';
import { useDashboard } from '../hooks/useDashboard';

export const Dashboard: React.FC = () => {
  const { dashboard, loading, error } = useDashboard();

  if (loading) {
    return (
      <div className="animate-pulse">
        <div className="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4 mb-8">
          {[...Array(4)].map((_, i) => (
            <div key={i} className="bg-gray-200 h-32 rounded-lg"></div>
          ))}
        </div>
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div className="bg-gray-200 h-96 rounded-lg"></div>
          <div className="bg-gray-200 h-96 rounded-lg"></div>
        </div>
      </div>
    );
  }

  if (error || !dashboard) {
    return (
      <div className="text-center py-12">
        <p className="text-red-600">{error || 'Erro ao carregar dashboard'}</p>
      </div>
    );
  }

  return (
    <div className="space-y-8">
      {/* Stats Cards */}
      <div className="grid grid-cols-1 gap-5 sm:grid-cols-2 lg:grid-cols-4">
        <StatsCard
          title="Saldo Total"
          value={dashboard.totalBalance}
          icon={<CreditCardIcon />}
          color="primary"
        />
        <StatsCard
          title="Receitas do Mês"
          value={dashboard.monthlyIncome}
          icon={<ArrowTrendingUpIcon />}
          color="success"
        />
        <StatsCard
          title="Despesas do Mês"
          value={dashboard.monthlyExpenses}
          icon={<ArrowTrendingDownIcon />}
          color="error"
        />
        <StatsCard
          title="Orçamento Usado"
          value={`${dashboard.budgetUsagePercentage.toFixed(1)}%`}
          icon={<BanknotesIcon />}
          color={dashboard.budgetUsagePercentage > 90 ? 'error' : 'warning'}
        />
      </div>

      {/* Main Content */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <RecentTransactions transactions={dashboard.recentTransactions} />
        <BudgetProgress budgets={dashboard.budgets} />
      </div>

      {/* Additional Charts/Content can be added here */}
    </div>
  );
};