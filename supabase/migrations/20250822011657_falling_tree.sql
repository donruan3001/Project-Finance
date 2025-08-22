/*
  # Update categories for US market

  1. Changes
    - Clear existing categories
    - Add US-focused categories with proper icons and colors
    - Update category names to match US terminology

  2. Categories
    - Housing & Utilities
    - Transportation
    - Food & Dining
    - Healthcare
    - Entertainment
    - Income categories
*/

-- Clear existing categories
DELETE FROM categories;

-- Insert US-focused categories
INSERT INTO categories (name, description, icon, color, type) VALUES
-- Expense Categories
('Housing', 'Rent, mortgage, property taxes', '🏠', '#FF6B6B', 'EXPENSE'),
('Utilities', 'Electricity, water, gas, internet', '⚡', '#4ECDC4', 'EXPENSE'),
('Transportation', 'Car payments, gas, public transport', '🚗', '#45B7D1', 'EXPENSE'),
('Groceries', 'Food shopping, household items', '🛒', '#96CEB4', 'EXPENSE'),
('Restaurants', 'Dining out, takeout, delivery', '🍽️', '#FFEAA7', 'EXPENSE'),
('Healthcare', 'Medical bills, insurance, pharmacy', '🏥', '#DDA0DD', 'EXPENSE'),
('Entertainment', 'Movies, streaming, hobbies', '🎬', '#FFB3BA', 'EXPENSE'),
('Shopping', 'Clothing, personal items', '🛍️', '#BFEFFF', 'EXPENSE'),
('Education', 'Tuition, books, courses', '📚', '#F0E68C', 'EXPENSE'),
('Insurance', 'Health, auto, life insurance', '🛡️', '#D3D3D3', 'EXPENSE'),
('Debt Payments', 'Credit cards, loans', '💳', '#FFA07A', 'EXPENSE'),
('Travel', 'Vacations, business trips', '✈️', '#98FB98', 'EXPENSE'),
('Pets', 'Pet food, vet bills, supplies', '🐕', '#F5DEB3', 'EXPENSE'),
('Gifts & Donations', 'Presents, charity', '🎁', '#E6E6FA', 'EXPENSE'),
('Personal Care', 'Haircuts, cosmetics, gym', '💄', '#FFE4E1', 'EXPENSE'),
('Technology', 'Phone, software, electronics', '📱', '#B0E0E6', 'EXPENSE'),
('Taxes', 'Income tax, property tax', '🏛️', '#C0C0C0', 'EXPENSE'),
('Bank Fees', 'ATM fees, service charges', '🏦', '#A9A9A9', 'EXPENSE'),

-- Income Categories
('Salary', 'Regular employment income', '💰', '#98D8C8', 'INCOME'),
('Freelance', 'Contract and freelance work', '💼', '#F7DC6F', 'INCOME'),
('Business Income', 'Revenue from business', '🏢', '#BB8FCE', 'INCOME'),
('Investment Income', 'Dividends, capital gains', '📈', '#85C1E9', 'INCOME'),
('Rental Income', 'Property rental income', '🏘️', '#82E0AA', 'INCOME'),
('Side Hustle', 'Part-time work, gig economy', '🚀', '#F8C471', 'INCOME'),
('Tax Refund', 'Government tax refunds', '💸', '#AED6F1', 'INCOME'),
('Bonus', 'Work bonuses, commissions', '🎯', '#A9DFBF', 'INCOME'),
('Gift Money', 'Money received as gifts', '🎁', '#F5B7B1', 'INCOME'),

-- Both (can be income or expense)
('Other', 'Miscellaneous transactions', '💡', '#D5DBDB', 'BOTH');