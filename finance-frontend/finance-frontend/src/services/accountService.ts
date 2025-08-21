import api from './api';
import { Account, CreateAccountRequest, PageResponse, PageRequest } from '../types';

export const accountService = {
  async createAccount(data: CreateAccountRequest): Promise<void> {
    await api.post('/accounts', data);
  },

  async getAccounts(params?: PageRequest): Promise<PageResponse<Account>> {
    const response = await api.get<PageResponse<Account>>('/accounts', { params });
    return response.data;
  },

  async updateAccount(id: number, data: Partial<Account>): Promise<Account> {
    const response = await api.patch<Account>(`/accounts/${id}`, data);
    return response.data;
  },

  async deleteAccount(id: number): Promise<void> {
    await api.delete(`/accounts/${id}`);
  }
};