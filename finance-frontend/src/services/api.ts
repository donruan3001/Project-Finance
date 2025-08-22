import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

// Create axios instance
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor to include auth token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('authToken');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// Auth API
export const authAPI = {
  register: (userData: {
    name: string;
    email: string;
    password: string;
    role: string;
  }) => api.post('/auth/register', userData),
  
  login: (credentials: {
    email: string;
    password: string;
  }) => api.post('/auth/login', credentials),
};

// User API
export const userAPI = {
  getProfile: (userId: number) => api.get(`/users/${userId}/profile`),
  updateProfile: (userId: number, data: any) => api.put(`/users/${userId}/profile`, data),
  deleteUser: (userId: number) => api.delete(`/users/${userId}`),
};

// Bank API
export const bankAPI = {
  create: (bankData: { name: string }) => api.post('/banks', bankData),
  getAll: () => api.get('/banks'),
  update: (id: number, data: { name: string }) => api.put(`/banks/${id}`, data),
  delete: (id: number) => api.delete(`/banks/${id}`),
};

// Account API
export const accountAPI = {
  create: (accountData: {
    userId: number;
    bankId: number;
    name: string;
    type: string;
    balance: number;
  }) => api.post('/accounts', accountData),
  
  getAll: (page = 0, size = 10) => api.get(`/accounts?page=${page}&size=${size}`),
  update: (id: number, data: any) => api.patch(`/accounts/${id}`, data),
  delete: (id: number) => api.delete(`/accounts/${id}`),
};

// Transaction API
export const transactionAPI = {
  create: (transactionData: {
    userId: number;
    accountId: number;
    name: string;
    category: string;
    type: string;
    amount: number;
  }) => api.post('/transactions', transactionData),
  
  getByUser: (userId: number, page = 0, size = 20) => 
    api.get(`/transactions/user/${userId}?page=${page}&size=${size}`),
  
  getRecent: (userId: number, limit = 10) => 
    api.get(`/transactions/user/${userId}/recent?limit=${limit}`),
  
  delete: (transactionId: number, userId: number) => 
    api.delete(`/transactions/${transactionId}/user/${userId}`),
};

// Budget API
export const budgetAPI = {
  create: (budgetData: {
    userId: number;
    categoryId: number;
    name: string;
    amount: number;
    startDate: string;
    endDate: string;
    period: string;
  }) => api.post('/budgets', budgetData),
  
  getByUser: (userId: number) => api.get(`/budgets/user/${userId}`),
  getActive: (userId: number, date?: string) => {
    const params = date ? `?date=${date}` : '';
    return api.get(`/budgets/user/${userId}/active${params}`);
  },
};

// Goal API
export const goalAPI = {
  create: (goalData: {
    userId: number;
    name: string;
    description?: string;
    targetAmount: number;
    targetDate: string;
    priority: string;
    icon?: string;
    color?: string;
  }) => api.post('/goals', goalData),
  
  getByUser: (userId: number) => api.get(`/goals/user/${userId}`),
  getActive: (userId: number) => api.get(`/goals/user/${userId}/active`),
  updateProgress: (goalId: number, amount: number) => 
    api.patch(`/goals/${goalId}/progress?amount=${amount}`),
};

// Category API
export const categoryAPI = {
  create: (categoryData: {
    name: string;
    description?: string;
    icon?: string;
    color?: string;
    type: string;
  }) => api.post('/categories', categoryData),
  
  getAll: () => api.get('/categories'),
  getByType: (type: string) => api.get(`/categories/type/${type}`),
  delete: (id: number) => api.delete(`/categories/${id}`),
};

// Dashboard API
export const dashboardAPI = {
  getDashboard: (userId: number) => api.get(`/dashboard/user/${userId}`),
};

// Analytics API
export const analyticsAPI = {
  getAnalytics: (userId: number, startDate: string, endDate: string) => 
    api.get(`/analytics/user/${userId}?startDate=${startDate}&endDate=${endDate}`),
  
  getCurrentMonth: (userId: number) => 
    api.get(`/analytics/user/${userId}/current-month`),
};

// Notification API
export const notificationAPI = {
  getAll: (userId: number) => api.get(`/notifications/user/${userId}`),
  getUnread: (userId: number) => api.get(`/notifications/user/${userId}/unread`),
  getCount: (userId: number) => api.get(`/notifications/user/${userId}/count`),
  markAsRead: (notificationId: number) => api.patch(`/notifications/${notificationId}/read`),
  markAllAsRead: (userId: number) => api.patch(`/notifications/user/${userId}/read-all`),
};

export default api;