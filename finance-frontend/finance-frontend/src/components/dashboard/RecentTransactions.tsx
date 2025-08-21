import React from 'react';
import { Card, CardHeader, CardTitle, CardContent } from '../ui/Card';
import { Badge } from '../ui/Badge';
import { RecentTransaction } from '../../types';
import { formatDistanceToNow } from 'date-fns';
import { ptBR } from 'date-fns/locale';

interface RecentTransactionsProps {
  transactions: RecentTransaction[];
}

export const RecentTransactions: React.FC<RecentTransactionsProps> = ({
  transactions
}) => {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Transações Recentes</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-4">
          {transactions.map((transaction) => (
            <div key={transaction.id} className="flex items-center justify-between">
              <div className="flex-1 min-w-0">
                <p className="text-sm font-medium text-gray-900 truncate">
                  {transaction.name}
                </p>
                <p className="text-sm text-gray-500">
                  {transaction.accountName} • {transaction.category}
                </p>
                <p className="text-xs text-gray-400">
                  {formatDistanceToNow(new Date(transaction.created), {
                    addSuffix: true,
                    locale: ptBR
                  })}
                </p>
              </div>
              <div className="flex items-center space-x-2">
                <Badge variant={transaction.type === 'INCOME' ? 'success' : 'error'}>
                  {transaction.type === 'INCOME' ? 'Receita' : 'Despesa'}
                </Badge>
                <span className={`text-sm font-semibold ${
                  transaction.type === 'INCOME' ? 'text-green-600' : 'text-red-600'
                }`}>
                  {transaction.type === 'INCOME' ? '+' : '-'}
                  {transaction.amount.toLocaleString('pt-BR', {
                    style: 'currency',
                    currency: 'BRL'
                  })}
                </span>
              </div>
            </div>
          ))}
          {transactions.length === 0 && (
            <p className="text-center text-gray-500 py-4">
              Nenhuma transação encontrada
            </p>
          )}
        </div>
      </CardContent>
    </Card>
  );
};