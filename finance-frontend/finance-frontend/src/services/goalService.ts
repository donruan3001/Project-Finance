import api from './api';
import { Goal, CreateGoalRequest } from '../types';

export const goalService = {
  async createGoal(data: CreateGoalRequest): Promise<Goal> {
    const response = await api.post<Goal>('/goals', data);
    return response.data;
  },

  async getGoalsByUser(userId: number): Promise<Goal[]> {
    const response = await api.get<Goal[]>(`/goals/user/${userId}`);
    return response.data;
  },

  async getActiveGoals(userId: number): Promise<Goal[]> {
    const response = await api.get<Goal[]>(`/goals/user/${userId}/active`);
    return response.data;
  },

  async updateGoalProgress(goalId: number, amount: number): Promise<Goal> {
    const response = await api.patch<Goal>(`/goals/${goalId}/progress`, null, {
      params: { amount }
    });
    return response.data;
  }
};