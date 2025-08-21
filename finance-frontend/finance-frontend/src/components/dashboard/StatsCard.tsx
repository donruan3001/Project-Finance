import React from 'react';
import { Card } from '../ui/Card';
import { cn } from '../../utils/cn';

interface StatsCardProps {
  title: string;
  value: string | number;
  change?: {
    value: number;
    type: 'increase' | 'decrease';
  };
  icon: React.ReactNode;
  color?: 'primary' | 'success' | 'warning' | 'error';
}

export const StatsCard: React.FC<StatsCardProps> = ({
  title,
  value,
  change,
  icon,
  color = 'primary'
}) => {
  const colors = {
    primary: 'text-primary-600 bg-primary-50',
    success: 'text-green-600 bg-green-50',
    warning: 'text-yellow-600 bg-yellow-50',
    error: 'text-red-600 bg-red-50'
  };

  return (
    <Card>
      <div className="flex items-center">
        <div className="flex-shrink-0">
          <div className={cn('p-3 rounded-lg', colors[color])}>
            <div className="w-6 h-6">{icon}</div>
          </div>
        </div>
        <div className="ml-5 w-0 flex-1">
          <dl>
            <dt className="text-sm font-medium text-gray-500 truncate">
              {title}
            </dt>
            <dd className="flex items-baseline">
              <div className="text-2xl font-semibold text-gray-900">
                {typeof value === 'number' 
                  ? value.toLocaleString('pt-BR', { 
                      style: 'currency', 
                      currency: 'BRL' 
                    })
                  : value
                }
              </div>
              {change && (
                <div className={cn(
                  'ml-2 flex items-baseline text-sm font-semibold',
                  change.type === 'increase' ? 'text-green-600' : 'text-red-600'
                )}>
                  {change.type === 'increase' ? '+' : '-'}
                  {Math.abs(change.value)}%
                </div>
              )}
            </dd>
          </dl>
        </div>
      </div>
    </Card>
  );
};