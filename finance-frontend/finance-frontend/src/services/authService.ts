import api from './api';
import { LoginRequest, RegisterRequest, AuthResponse, UserProfile } from '../types';

export const authService = {
  async login(credentials: LoginRequest): Promise<AuthResponse> {
    const response = await api.post<AuthResponse>('/auth/login', credentials);
    return response.data;
  },

  async register(userData: RegisterRequest): Promise<void> {
    await api.post('/auth/register', userData);
  },

  async getProfile(userId: number): Promise<UserProfile> {
    const response = await api.get<UserProfile>(`/users/${userId}/profile`);
    return response.data;
  },

  async updateProfile(userId: number, data: Partial<UserProfile>): Promise<UserProfile> {
    const response = await api.put<UserProfile>(`/users/${userId}/profile`, data);
    return response.data;
  },

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login';
  },

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  },

  getCurrentUser(): UserProfile | null {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  },

  setAuthData(token: string, user: UserProfile): void {
    localStorage.setItem('token', token);
    localStorage.setItem('user', JSON.stringify(user));
  }
};