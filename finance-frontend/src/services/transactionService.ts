import api from './api';
import { Transaction, CreateTransactionRequest, PageResponse, PageRequest } from '../types';

export const transactionService = {
  async createTransaction(data: CreateTransactionRequest): Promise<void> {
    await api.post('/transactions', data);
  },

  async getTransactionsByUser(userId: number, params?: PageRequest): Promise<PageResponse<Transaction>> {
    const response = await api.get<PageResponse<Transaction>>(`/transactions/user/${userId}`, { params });
    return response.data;
  },

  async getRecentTransactions(userId: number, limit: number = 10): Promise<Transaction[]> {
    const response = await api.get<Transaction[]>(`/transactions/user/${userId}/recent`, {
      params: { limit }
    });
    return response.data;
  },

  async deleteTransaction(transactionId: number, userId: number): Promise<void> {
    await api.delete(`/transactions/${transactionId}/user/${userId}`);
  }
};