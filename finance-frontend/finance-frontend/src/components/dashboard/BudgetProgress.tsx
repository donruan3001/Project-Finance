import React from 'react';
import { Card, CardHeader, CardTitle, CardContent } from '../ui/Card';
import { ProgressBar } from '../ui/ProgressBar';
import { BudgetSummary } from '../../types';

interface BudgetProgressProps {
  budgets: BudgetSummary[];
}

export const BudgetProgress: React.FC<BudgetProgressProps> = ({ budgets }) => {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Progresso dos Orçamentos</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-6">
          {budgets.map((budget) => (
            <div key={budget.id}>
              <div className="flex items-center justify-between mb-2">
                <div>
                  <p className="text-sm font-medium text-gray-900">
                    {budget.name}
                  </p>
                  <p className="text-xs text-gray-500">
                    {budget.categoryName}
                  </p>
                </div>
                <div className="text-right">
                  <p className="text-sm font-semibold text-gray-900">
                    {budget.spent.toLocaleString('pt-BR', {
                      style: 'currency',
                      currency: 'BRL'
                    })}
                  </p>
                  <p className="text-xs text-gray-500">
                    de {budget.amount.toLocaleString('pt-BR', {
                      style: 'currency',
                      currency: 'BRL'
                    })}
                  </p>
                </div>
              </div>
              <ProgressBar
                value={budget.spent}
                max={budget.amount}
                color={budget.progressPercentage > 90 ? 'error' : 
                       budget.progressPercentage > 70 ? 'warning' : 'success'}
              />
              <div className="flex justify-between text-xs text-gray-500 mt-1">
                <span>{budget.progressPercentage.toFixed(1)}% usado</span>
                <span>
                  {(budget.amount - budget.spent).toLocaleString('pt-BR', {
                    style: 'currency',
                    currency: 'BRL'
                  })} restante
                </span>
              </div>
            </div>
          ))}
          {budgets.length === 0 && (
            <p className="text-center text-gray-500 py-4">
              Nenhum orçamento encontrado
            </p>
          )}
        </div>
      </CardContent>
    </Card>
  );
};