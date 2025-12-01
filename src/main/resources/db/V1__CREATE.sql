/* ========================================
   1. USERS (mantido)
======================================== */
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/* ========================================
   2. BANKS (mantido)
======================================== */
CREATE TABLE IF NOT EXISTS banks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

/* ========================================
   3. ACCOUNTS (mantida + melhorias)
======================================== */
CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    bank_id BIGINT NULL,
    name VARCHAR(120) NOT NULL,
    type VARCHAR(30) NOT NULL DEFAULT 'corrente',
    balance DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (bank_id) REFERENCES banks(id) ON DELETE SET NULL
);

/* Índices importantes */
CREATE INDEX idx_accounts_user ON accounts(user_id);
CREATE INDEX idx_accounts_bank ON accounts(bank_id);

/* ========================================
   4. CATEGORIES (NOVA)
======================================== */
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NULL,
    name VARCHAR(50) NOT NULL,
    type ENUM('income','expense') NOT NULL,
    icon VARCHAR(50) NULL,
    color VARCHAR(20) NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

/* ========================================
   5. TRANSACTIONS (mantida + expansão)
======================================== */
CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    account_id BIGINT NOT NULL,
    category_id BIGINT NULL,

    name VARCHAR(80) NULL,
    type ENUM('income','expense','transfer') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,

    /* Parcelamentos */
    installment_group VARCHAR(40) NULL,
    installment_number INT NULL,
    installment_total INT NULL,

    /* Recorrência */
    recurrence_id BIGINT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL
);

CREATE INDEX idx_transactions_account ON transactions(account_id);
CREATE INDEX idx_transactions_category ON transactions(category_id);

/* ========================================
   6. RECURRENCE EVENTS
======================================== */
CREATE TABLE IF NOT EXISTS recurrence_events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    frequency ENUM('daily','weekly','monthly','yearly') NOT NULL,
    next_run DATE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

/* ========================================
   7. ATTACHMENTS (comprovantes)
======================================== */
CREATE TABLE IF NOT EXISTS transaction_attachments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id BIGINT NOT NULL,
    file_url TEXT NOT NULL,
    uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE
);

/* ========================================
   8. GOALS (Metas financeiras)
======================================== */
CREATE TABLE IF NOT EXISTS goals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    target_amount DECIMAL(10,2) NOT NULL,
    saved_amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    deadline DATE NOT NULL,
    status ENUM('active','completed','canceled') DEFAULT 'active',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

/* ========================================
   9. BUDGETS (Orçamentos mensais)
======================================== */
CREATE TABLE IF NOT EXISTS budgets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    limit_amount DECIMAL(10,2) NOT NULL,
    month INT NOT NULL,
    year INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

/* ========================================
   10. INSIGHTS (para IA futura)
======================================== */
CREATE TABLE IF NOT EXISTS insights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(150),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
