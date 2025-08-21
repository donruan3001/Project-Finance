import { useState, useEffect, createContext, useContext, ReactNode } from 'react';
import { authService } from '../services/authService';
import { UserProfile, LoginRequest, RegisterRequest } from '../types';
import toast from 'react-hot-toast';

interface AuthContextType {
  user: UserProfile | null;
  loading: boolean;
  login: (credentials: LoginRequest) => Promise<void>;
  register: (userData: RegisterRequest) => Promise<void>;
  logout: () => void;
  updateProfile: (data: Partial<UserProfile>) => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [user, setUser] = useState<UserProfile | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const initAuth = async () => {
      try {
        const currentUser = authService.getCurrentUser();
        if (currentUser && authService.isAuthenticated()) {
          // Verify token is still valid by fetching fresh profile
          const profile = await authService.getProfile(currentUser.id);
          setUser(profile);
        }
      } catch (error) {
        // Token is invalid, clear auth data
        authService.logout();
      } finally {
        setLoading(false);
      }
    };

    initAuth();
  }, []);

  const login = async (credentials: LoginRequest) => {
    try {
      const authResponse = await authService.login(credentials);
      
      // Decode JWT to get user info (simple decode, in production use proper JWT library)
      const payload = JSON.parse(atob(authResponse.tokenJwt.split('.')[1]));
      const userProfile = await authService.getProfile(payload.sub);
      
      authService.setAuthData(authResponse.tokenJwt, userProfile);
      setUser(userProfile);
      toast.success('Login realizado com sucesso!');
    } catch (error) {
      toast.error('Credenciais inválidas');
      throw error;
    }
  };

  const register = async (userData: RegisterRequest) => {
    try {
      await authService.register(userData);
      toast.success('Conta criada com sucesso! Faça login para continuar.');
    } catch (error) {
      toast.error('Erro ao criar conta');
      throw error;
    }
  };

  const logout = () => {
    authService.logout();
    setUser(null);
    toast.success('Logout realizado com sucesso!');
  };

  const updateProfile = async (data: Partial<UserProfile>) => {
    if (!user) return;
    
    try {
      const updatedProfile = await authService.updateProfile(user.id, data);
      setUser(updatedProfile);
      authService.setAuthData(localStorage.getItem('token')!, updatedProfile);
      toast.success('Perfil atualizado com sucesso!');
    } catch (error) {
      toast.error('Erro ao atualizar perfil');
      throw error;
    }
  };

  return (
    <AuthContext.Provider value={{
      user,
      loading,
      login,
      register,
      logout,
      updateProfile
    }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};