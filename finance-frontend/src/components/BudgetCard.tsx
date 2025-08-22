import React from 'react';
import { Target, TrendingUp, AlertTriangle } from 'lucide-react';

interface BudgetCardProps {
  budget: {
    id: number;
    name: string;
    categoryName: string;
    amount: number;
    spent: number;
    progressPercentage: number;
    period: string;
  };
}

const BudgetCard: React.FC<BudgetCardProps> = ({ budget }) => {
  const remaining = budget.amount - budget.spent;
  const isOverBudget = budget.spent > budget.amount;
  const isNearLimit = budget.progressPercentage > 80 && !isOverBudget;

  const getProgressColor = () => {
    if (isOverBudget) return 'bg-red-500';
    if (isNearLimit) return 'bg-yellow-500';
    return 'bg-green-500';
  };

  const getStatusIcon = () => {
    if (isOverBudget) return <AlertTriangle className="h-5 w-5 text-red-500" />;
    if (isNearLimit) return <AlertTriangle className="h-5 w-5 text-yellow-500" />;
    return <TrendingUp className="h-5 w-5 text-green-500" />;
  };

  return (
    <div className="card hover:shadow-lg transition-shadow">
      <div className="flex items-start justify-between mb-4">
        <div className="flex items-center">
          <div className="p-2 bg-primary-100 rounded-lg mr-3">
            <Target className="h-6 w-6 text-primary-600" />
          </div>
          <div>
            <h3 className="font-semibold text-gray-900">{budget.name}</h3>
            <p className="text-sm text-gray-500">{budget.categoryName}</p>
          </div>
        </div>
        {getStatusIcon()}
      </div>

      <div className="space-y-3">
        <div className="flex justify-between items-center">
          <span className="text-sm text-gray-600">Progress</span>
          <span className="text-sm font-medium text-gray-900">
            {Math.min(budget.progressPercentage, 100).toFixed(0)}%
          </span>
        </div>

        <div className="w-full bg-gray-200 rounded-full h-3">
          <div
            className={`h-3 rounded-full transition-all duration-300 ${getProgressColor()}`}
            style={{ width: `${Math.min(budget.progressPercentage, 100)}%` }}
          />
        </div>

        <div className="flex justify-between items-center text-sm">
          <div>
            <span className="text-gray-600">Spent: </span>
            <span className={`font-medium ${isOverBudget ? 'text-red-600' : 'text-gray-900'}`}>
              ${budget.spent.toLocaleString()}
            </span>
          </div>
          <div>
            <span className="text-gray-600">Budget: </span>
            <span className="font-medium text-gray-900">
              ${budget.amount.toLocaleString()}
            </span>
          </div>
        </div>

        <div className="pt-2 border-t border-gray-100">
          <div className="flex justify-between items-center">
            <span className="text-sm text-gray-600">
              {isOverBudget ? 'Over budget:' : 'Remaining:'}
            </span>
            <span className={`font-semibold ${
              isOverBudget ? 'text-red-600' : 'text-green-600'
            }`}>
              ${Math.abs(remaining).toLocaleString()}
            </span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BudgetCard;