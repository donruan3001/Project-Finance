import api from './api';
import { Dashboard } from '../types';

export const dashboardService = {
  async getDashboard(userId: number): Promise<Dashboard> {
    const response = await api.get<Dashboard>(`/dashboard/user/${userId}`);
    return response.data;
  }
};