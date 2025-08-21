import api from './api';
import { Budget, CreateBudgetRequest } from '../types';

export const budgetService = {
  async createBudget(data: CreateBudgetRequest): Promise<Budget> {
    const response = await api.post<Budget>('/budgets', data);
    return response.data;
  },

  async getBudgetsByUser(userId: number): Promise<Budget[]> {
    const response = await api.get<Budget[]>(`/budgets/user/${userId}`);
    return response.data;
  },

  async getActiveBudgets(userId: number, date?: string): Promise<Budget[]> {
    const response = await api.get<Budget[]>(`/budgets/user/${userId}/active`, {
      params: date ? { date } : undefined
    });
    return response.data;
  }
};