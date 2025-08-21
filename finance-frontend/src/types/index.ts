// API Response Types
export interface ApiResponse<T> {
  data: T;
  message?: string;
  status: number;
}

// User Types
export interface User {
  id: number;
  name: string;
  email: string;
  role: 'USER' | 'ADMIN';
}

export interface UserProfile {
  id: number;
  name: string;
  email: string;
  role: 'USER' | 'ADMIN';
}

// Auth Types
export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
  role: 'USER' | 'ADMIN';
}

export interface AuthResponse {
  tokenJwt: string;
}

// Account Types
export interface Account {
  id: number;
  userId: number;
  bankId: number;
  name: string;
  type: AccountType;
  balance: number;
  createdAt: string;
}

export type AccountType = 
  | 'CONTA_CONJUNTA' | 'CONTA_CORRENTE' | 'CONTA_CREDITO' 
  | 'CONTA_DIGITAL' | 'CONTA_EMPRESARIAL' | 'CONTA_ESTUDANTIL'
  | 'CONTA_FIDELIDADE' | 'CONTA_INTERNACIONAL' | 'CONTA_INVESTIMENTO'
  | 'CONTA_POUPANCA' | 'CONTA_SALARIO' | 'CONTA_UNIVERSITARIA';

export interface CreateAccountRequest {
  userId: number;
  bankId: number;
  name: string;
  type: AccountType;
  balance: number;
}

// Bank Types
export interface Bank {
  id: number;
  name: string;
}

export interface CreateBankRequest {
  name: string;
}

// Transaction Types
export interface Transaction {
  id: number;
  userId: number;
  accountId: number;
  category: CategoryTransactions;
  name: string;
  amount: number;
  type: TransactionType;
  created: string;
  updated: string;
}

export type TransactionType = 'INCOME' | 'EXPENSE';

export type CategoryTransactions = 
  | 'MORADIA' | 'TRANSPORTE' | 'ALIMENTACAO' | 'RESTAURANTES' 
  | 'DELIVERY' | 'PARCELAS' | 'JUROS_MULTAS' | 'TARIFAS_BANCARIAS'
  | 'EMPRESTIMOS' | 'MATERIAIS_ESCRITORIO' | 'SOFTWARES' 
  | 'CURSOS_TREINAMENTOS' | 'ROUPAS' | 'BELEZA' | 'HIGIENE'
  | 'PLANO_SAUDE' | 'CONSULTAS' | 'FARMACIA' | 'CINEMA' 
  | 'SHOWS' | 'VIAGENS' | 'STREAMING' | 'MENSALIDADE_ESCOLAR'
  | 'CURSOS_ONLINE' | 'LIVROS' | 'PRESENTES' | 'DOACOES'
  | 'ALIMENTACAO_PET' | 'VETERINARIO';

export interface CreateTransactionRequest {
  userId: number;
  accountId: number;
  name: string;
  category: CategoryTransactions;
  type: TransactionType;
  amount: number;
}

// Category Types
export interface Category {
  id: number;
  name: string;
  description?: string;
  icon?: string;
  color?: string;
  type: CategoryType;
  isActive: boolean;
  createdAt: string;
}

export type CategoryType = 'INCOME' | 'EXPENSE' | 'BOTH';

export interface CreateCategoryRequest {
  name: string;
  description?: string;
  icon?: string;
  color?: string;
  type: CategoryType;
}

// Budget Types
export interface Budget {
  id: number;
  userId: number;
  categoryId: number;
  categoryName: string;
  name: string;
  amount: number;
  spent: number;
  remaining: number;
  progressPercentage: number;
  startDate: string;
  endDate: string;
  period: BudgetPeriod;
  isActive: boolean;
  createdAt: string;
}

export type BudgetPeriod = 'WEEKLY' | 'MONTHLY' | 'QUARTERLY' | 'YEARLY' | 'CUSTOM';

export interface CreateBudgetRequest {
  userId: number;
  categoryId: number;
  name: string;
  amount: number;
  startDate: string;
  endDate: string;
  period: BudgetPeriod;
}

// Goal Types
export interface Goal {
  id: number;
  userId: number;
  name: string;
  description?: string;
  targetAmount: number;
  currentAmount: number;
  progressPercentage: number;
  targetDate: string;
  status: GoalStatus;
  priority: GoalPriority;
  icon?: string;
  color?: string;
  daysRemaining: number;
  createdAt: string;
}

export type GoalStatus = 'ACTIVE' | 'COMPLETED' | 'PAUSED' | 'CANCELLED';
export type GoalPriority = 'LOW' | 'MEDIUM' | 'HIGH' | 'URGENT';

export interface CreateGoalRequest {
  userId: number;
  name: string;
  description?: string;
  targetAmount: number;
  targetDate: string;
  priority: GoalPriority;
  icon?: string;
  color?: string;
}

// Dashboard Types
export interface Dashboard {
  totalBalance: number;
  monthlyIncome: number;
  monthlyExpenses: number;
  monthlyBudget: number;
  budgetUsagePercentage: number;
  accounts: AccountSummary[];
  recentTransactions: RecentTransaction[];
  budgets: BudgetSummary[];
  goals: GoalSummary[];
  expensesByCategory: Record<string, number>;
  incomeByCategory: Record<string, number>;
  monthlyTrends: MonthlyTrend[];
}

export interface AccountSummary {
  id: number;
  name: string;
  type: AccountType;
  balance: number;
  bankName: string;
}

export interface RecentTransaction {
  id: number;
  name: string;
  category: string;
  type: TransactionType;
  amount: number;
  accountName: string;
  created: string;
}

export interface BudgetSummary {
  id: number;
  name: string;
  categoryName: string;
  amount: number;
  spent: number;
  progressPercentage: number;
  status: string;
}

export interface GoalSummary {
  id: number;
  name: string;
  targetAmount: number;
  currentAmount: number;
  progressPercentage: number;
  targetDate: string;
  priority: GoalPriority;
  icon?: string;
  color?: string;
}

export interface MonthlyTrend {
  month: string;
  income: number;
  expenses: number;
  balance: number;
}

// Analytics Types
export interface Analytics {
  expensesByCategory: Record<string, number>;
  incomeByCategory: Record<string, number>;
  monthlyComparison: MonthlyComparison[];
  categoryTrends: CategoryTrend[];
  averageMonthlyIncome: number;
  averageMonthlyExpenses: number;
  savingsRate: number;
  topExpenseCategory: string;
  topIncomeCategory: string;
  budgetPerformance: BudgetPerformance[];
}

export interface MonthlyComparison {
  month: string;
  income: number;
  expenses: number;
  savings: number;
  savingsRate: number;
}

export interface CategoryTrend {
  categoryName: string;
  monthlyValues: MonthlyValue[];
  averageAmount: number;
  trend: string;
}

export interface MonthlyValue {
  month: string;
  amount: number;
}

export interface BudgetPerformance {
  budgetName: string;
  categoryName: string;
  budgetAmount: number;
  actualAmount: number;
  variance: number;
  performance: string;
}

// Notification Types
export interface Notification {
  id: number;
  title: string;
  message: string;
  type: NotificationType;
  isRead: boolean;
  actionUrl?: string;
  createdAt: string;
  readAt?: string;
}

export type NotificationType = 
  | 'BUDGET_ALERT' | 'GOAL_REMINDER' | 'TRANSACTION_ALERT' 
  | 'BILL_REMINDER' | 'SECURITY_ALERT' | 'SYSTEM_UPDATE';

// Pagination Types
export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}

export interface PageRequest {
  page?: number;
  size?: number;
  sort?: string;
}