import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Wallet, TrendingUp, PieChart, Target } from 'lucide-react';

// Components
import Dashboard from './components/Dashboard';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-50">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/" element={<Dashboard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;