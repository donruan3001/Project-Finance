import React, { useState } from 'react';
import { X, DollarSign, Calendar, Tag, CreditCard } from 'lucide-react';

interface TransactionFormProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (transaction: any) => void;
}

const TransactionForm: React.FC<TransactionFormProps> = ({ isOpen, onClose, onSubmit }) => {
  const [formData, setFormData] = useState({
    name: '',
    amount: '',
    type: 'EXPENSE',
    category: '',
    accountId: '1',
    date: new Date().toISOString().split('T')[0]
  });

  const categories = {
    EXPENSE: [
      { value: 'HOUSING', label: 'Housing' },
      { value: 'TRANSPORTATION', label: 'Transportation' },
      { value: 'GROCERIES', label: 'Groceries' },
      { value: 'RESTAURANTS', label: 'Restaurants' },
      { value: 'HEALTHCARE', label: 'Healthcare' },
      { value: 'ENTERTAINMENT', label: 'Entertainment' },
      { value: 'SHOPPING', label: 'Shopping' },
      { value: 'UTILITIES', label: 'Utilities' }
    ],
    INCOME: [
      { value: 'SALARY', label: 'Salary' },
      { value: 'FREELANCE', label: 'Freelance' },
      { value: 'BUSINESS_INCOME', label: 'Business Income' },
      { value: 'INVESTMENT_INCOME', label: 'Investment Income' },
      { value: 'RENTAL_INCOME', label: 'Rental Income' },
      { value: 'BONUS', label: 'Bonus' }
    ]
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({
      ...formData,
      amount: parseFloat(formData.amount),
      userId: 1 // This should come from auth context
    });
    onClose();
    setFormData({
      name: '',
      amount: '',
      type: 'EXPENSE',
      category: '',
      accountId: '1',
      date: new Date().toISOString().split('T')[0]
    });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value,
      // Reset category when type changes
      ...(name === 'type' && { category: '' })
    }));
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-md mx-4">
        <div className="flex justify-between items-center mb-6">
          <h2 className="text-xl font-semibold text-gray-900">Add Transaction</h2>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 transition-colors"
          >
            <X className="h-6 w-6" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Description
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className="input-field"
              placeholder="e.g., Grocery shopping"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Amount
            </label>
            <div className="relative">
              <DollarSign className="absolute left-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400" />
              <input
                type="number"
                name="amount"
                value={formData.amount}
                onChange={handleChange}
                className="input-field pl-10"
                placeholder="0.00"
                step="0.01"
                min="0"
                required
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Type
            </label>
            <select
              name="type"
              value={formData.type}
              onChange={handleChange}
              className="input-field"
              required
            >
              <option value="EXPENSE">Expense</option>
              <option value="INCOME">Income</option>
            </select>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Category
            </label>
            <div className="relative">
              <Tag className="absolute left-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400" />
              <select
                name="category"
                value={formData.category}
                onChange={handleChange}
                className="input-field pl-10"
                required
              >
                <option value="">Select category</option>
                {categories[formData.type as keyof typeof categories].map(cat => (
                  <option key={cat.value} value={cat.value}>
                    {cat.label}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Date
            </label>
            <div className="relative">
              <Calendar className="absolute left-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-gray-400" />
              <input
                type="date"
                name="date"
                value={formData.date}
                onChange={handleChange}
                className="input-field pl-10"
                required
              />
            </div>
          </div>

          <div className="flex space-x-3 pt-4">
            <button
              type="button"
              onClick={onClose}
              className="flex-1 btn-secondary"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="flex-1 btn-primary"
            >
              Add Transaction
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default TransactionForm;