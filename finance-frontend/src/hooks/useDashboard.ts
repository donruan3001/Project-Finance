import { useState, useEffect } from 'react';
import { dashboardService } from '../services/dashboardService';
import { Dashboard } from '../types';
import { useAuth } from './useAuth';

export const useDashboard = () => {
  const { user } = useAuth();
  const [dashboard, setDashboard] = useState<Dashboard | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchDashboard = async () => {
    if (!user) return;

    try {
      setLoading(true);
      setError(null);
      const data = await dashboardService.getDashboard(user.id);
      setDashboard(data);
    } catch (err) {
      setError('Erro ao carregar dashboard');
      console.error('Dashboard error:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchDashboard();
  }, [user]);

  return {
    dashboard,
    loading,
    error,
    refetch: fetchDashboard
  };
};