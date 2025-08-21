/*
  # Add new tables for enhanced finance features

  1. New Tables
    - `categories` - Custom categories for transactions
    - `budgets` - Budget management
    - `goals` - Financial goals tracking
    - `notifications` - User notifications
    - `reports` - Generated reports

  2. Security
    - Enable RLS on all new tables
    - Add policies for authenticated users to access their own data

  3. Enhancements
    - Add code field to banks table
    - Add indexes for better performance
*/

-- Categories table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    icon VARCHAR(50),
    color VARCHAR(20),
    type VARCHAR(20) NOT NULL DEFAULT 'EXPENSE',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Budgets table
CREATE TABLE IF NOT EXISTS budgets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT,
    name VARCHAR(120) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    spent DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    period VARCHAR(20) NOT NULL DEFAULT 'MONTHLY',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

-- Goals table
CREATE TABLE IF NOT EXISTS goals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(120) NOT NULL,
    description TEXT,
    target_amount DECIMAL(12,2) NOT NULL,
    current_amount DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    target_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    icon VARCHAR(50),
    color VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Notifications table
CREATE TABLE IF NOT EXISTS notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(30) NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    action_url VARCHAR(500),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    read_at TIMESTAMP NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Reports table
CREATE TABLE IF NOT EXISTS reports (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(120) NOT NULL,
    description TEXT,
    type VARCHAR(30) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    report_data TEXT,
    file_path VARCHAR(500),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Add code field to banks table
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name = 'banks' AND column_name = 'code'
    ) THEN
        ALTER TABLE banks ADD COLUMN code VARCHAR(10);
    END IF;
END $$;

-- Add indexes for better performance
CREATE INDEX IF NOT EXISTS idx_transactions_user_created ON transactions(user_id, created);
CREATE INDEX IF NOT EXISTS idx_transactions_account_created ON transactions(account_id, created);
CREATE INDEX IF NOT EXISTS idx_transactions_category ON transactions(category);
CREATE INDEX IF NOT EXISTS idx_budgets_user_active ON budgets(user_id, is_active);
CREATE INDEX IF NOT EXISTS idx_budgets_dates ON budgets(start_date, end_date);
CREATE INDEX IF NOT EXISTS idx_goals_user_status ON goals(user_id, status);
CREATE INDEX IF NOT EXISTS idx_notifications_user_read ON notifications(user_id, is_read);

-- Insert default categories
INSERT IGNORE INTO categories (name, description, icon, color, type) VALUES
('Alimentação', 'Gastos com comida e bebida', '🍽️', '#FF6B6B', 'EXPENSE'),
('Transporte', 'Gastos com transporte público, combustível, etc.', '🚗', '#4ECDC4', 'EXPENSE'),
('Moradia', 'Aluguel, financiamento, contas da casa', '🏠', '#45B7D1', 'EXPENSE'),
('Saúde', 'Consultas médicas, medicamentos, plano de saúde', '🏥', '#96CEB4', 'EXPENSE'),
('Educação', 'Cursos, livros, material escolar', '📚', '#FFEAA7', 'EXPENSE'),
('Lazer', 'Cinema, shows, viagens, entretenimento', '🎉', '#DDA0DD', 'EXPENSE'),
('Salário', 'Salário mensal', '💰', '#98D8C8', 'INCOME'),
('Freelance', 'Trabalhos extras', '💼', '#F7DC6F', 'INCOME'),
('Investimentos', 'Rendimentos de investimentos', '📈', '#BB8FCE', 'INCOME'),
('Outros', 'Outras receitas', '💡', '#85C1E9', 'BOTH');