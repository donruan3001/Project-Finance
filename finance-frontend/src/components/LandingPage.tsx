import React from 'react';
import { Link } from 'react-router-dom';
import { 
  Wallet, 
  TrendingUp, 
  Shield, 
  Smartphone, 
  BarChart3, 
  Target,
  CheckCircle,
  Star,
  ArrowRight,
  DollarSign,
  PieChart,
  CreditCard
} from 'lucide-react';

const LandingPage: React.FC = () => {
  return (
    <div className="min-h-screen bg-white">
      {/* Header */}
      <header className="bg-white shadow-sm border-b border-gray-200 sticky top-0 z-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center h-16">
            <div className="flex items-center">
              <Wallet className="h-8 w-8 text-primary-600" />
              <span className="ml-2 text-xl font-bold text-gray-900">FinanceFlow</span>
            </div>
            <nav className="hidden md:flex space-x-8">
              <a href="#features" className="text-gray-600 hover:text-gray-900 transition-colors">Features</a>
              <a href="#pricing" className="text-gray-600 hover:text-gray-900 transition-colors">Pricing</a>
              <a href="#testimonials" className="text-gray-600 hover:text-gray-900 transition-colors">Reviews</a>
              <a href="#contact" className="text-gray-600 hover:text-gray-900 transition-colors">Contact</a>
            </nav>
            <div className="flex items-center space-x-4">
              <Link to="/login" className="text-gray-600 hover:text-gray-900 transition-colors">
                Sign In
              </Link>
              <Link to="/register" className="btn-primary">
                Get Started Free
              </Link>
            </div>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="bg-gradient-to-br from-primary-50 to-white py-20">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
            <div className="animate-fade-in">
              <h1 className="text-4xl md:text-6xl font-bold text-gray-900 leading-tight">
                Take Control of Your
                <span className="text-primary-600"> Financial Future</span>
              </h1>
              <p className="text-xl text-gray-600 mt-6 leading-relaxed">
                Smart budgeting, expense tracking, and financial planning made simple. 
                Join thousands of Americans building wealth with FinanceFlow.
              </p>
              <div className="mt-8 flex flex-col sm:flex-row gap-4">
                <Link to="/register" className="btn-primary text-lg px-8 py-4 flex items-center justify-center">
                  Start Free Trial
                  <ArrowRight className="ml-2 h-5 w-5" />
                </Link>
                <button className="btn-secondary text-lg px-8 py-4">
                  Watch Demo
                </button>
              </div>
              <div className="mt-8 flex items-center space-x-6 text-sm text-gray-500">
                <div className="flex items-center">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-2" />
                  Free 30-day trial
                </div>
                <div className="flex items-center">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-2" />
                  No credit card required
                </div>
                <div className="flex items-center">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-2" />
                  Cancel anytime
                </div>
              </div>
            </div>
            <div className="relative animate-slide-up">
              <div className="bg-white rounded-2xl shadow-2xl p-8 border border-gray-200">
                <div className="space-y-6">
                  <div className="flex items-center justify-between">
                    <h3 className="text-lg font-semibold text-gray-900">Monthly Overview</h3>
                    <span className="text-sm text-gray-500">December 2024</span>
                  </div>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="bg-green-50 p-4 rounded-lg">
                      <div className="flex items-center">
                        <TrendingUp className="h-6 w-6 text-green-600" />
                        <div className="ml-3">
                          <p className="text-sm text-green-600">Income</p>
                          <p className="text-xl font-bold text-green-700">$8,450</p>
                        </div>
                      </div>
                    </div>
                    <div className="bg-red-50 p-4 rounded-lg">
                      <div className="flex items-center">
                        <PieChart className="h-6 w-6 text-red-600" />
                        <div className="ml-3">
                          <p className="text-sm text-red-600">Expenses</p>
                          <p className="text-xl font-bold text-red-700">$3,250</p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="bg-primary-50 p-4 rounded-lg">
                    <div className="flex items-center justify-between">
                      <div>
                        <p className="text-sm text-primary-600">Savings Goal</p>
                        <p className="text-lg font-bold text-primary-700">Emergency Fund</p>
                      </div>
                      <div className="text-right">
                        <p className="text-sm text-primary-600">75% Complete</p>
                        <div className="w-24 bg-primary-200 rounded-full h-2 mt-1">
                          <div className="bg-primary-600 h-2 rounded-full" style={{ width: '75%' }}></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-20 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
              Everything You Need to Master Your Money
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              Powerful features designed to help Americans achieve financial freedom
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {[
              {
                icon: <DollarSign className="h-8 w-8 text-primary-600" />,
                title: "Smart Budgeting",
                description: "Create and track budgets with AI-powered insights. Get alerts before you overspend."
              },
              {
                icon: <BarChart3 className="h-8 w-8 text-primary-600" />,
                title: "Expense Analytics",
                description: "Visualize your spending patterns with detailed charts and reports."
              },
              {
                icon: <Target className="h-8 w-8 text-primary-600" />,
                title: "Goal Tracking",
                description: "Set and achieve financial goals with personalized savings plans."
              },
              {
                icon: <CreditCard className="h-8 w-8 text-primary-600" />,
                title: "Account Sync",
                description: "Connect all your bank accounts and credit cards for automatic tracking."
              },
              {
                icon: <Shield className="h-8 w-8 text-primary-600" />,
                title: "Bank-Level Security",
                description: "Your data is protected with 256-bit encryption and multi-factor authentication."
              },
              {
                icon: <Smartphone className="h-8 w-8 text-primary-600" />,
                title: "Mobile App",
                description: "Track expenses on the go with our iOS and Android apps."
              }
            ].map((feature, index) => (
              <div key={index} className="card hover:shadow-lg transition-shadow duration-300">
                <div className="mb-4">{feature.icon}</div>
                <h3 className="text-xl font-semibold text-gray-900 mb-3">{feature.title}</h3>
                <p className="text-gray-600">{feature.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Pricing Section */}
      <section id="pricing" className="py-20 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
              Simple, Transparent Pricing
            </h2>
            <p className="text-xl text-gray-600">
              Choose the plan that fits your financial journey
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 max-w-5xl mx-auto">
            {[
              {
                name: "Starter",
                price: "Free",
                period: "forever",
                description: "Perfect for getting started",
                features: [
                  "Up to 3 bank accounts",
                  "Basic expense tracking",
                  "Monthly reports",
                  "Mobile app access"
                ],
                cta: "Get Started",
                popular: false
              },
              {
                name: "Pro",
                price: "$9.99",
                period: "per month",
                description: "For serious budgeters",
                features: [
                  "Unlimited accounts",
                  "Advanced analytics",
                  "Goal tracking",
                  "Bill reminders",
                  "Investment tracking",
                  "Priority support"
                ],
                cta: "Start Free Trial",
                popular: true
              },
              {
                name: "Family",
                price: "$19.99",
                period: "per month",
                description: "For families and couples",
                features: [
                  "Everything in Pro",
                  "Up to 5 family members",
                  "Shared budgets",
                  "Family goals",
                  "Spending allowances",
                  "Financial advisor access"
                ],
                cta: "Start Free Trial",
                popular: false
              }
            ].map((plan, index) => (
              <div key={index} className={`card relative ${plan.popular ? 'ring-2 ring-primary-600 scale-105' : ''}`}>
                {plan.popular && (
                  <div className="absolute -top-4 left-1/2 transform -translate-x-1/2">
                    <span className="bg-primary-600 text-white px-4 py-1 rounded-full text-sm font-medium">
                      Most Popular
                    </span>
                  </div>
                )}
                <div className="text-center mb-6">
                  <h3 className="text-xl font-semibold text-gray-900 mb-2">{plan.name}</h3>
                  <div className="mb-2">
                    <span className="text-4xl font-bold text-gray-900">{plan.price}</span>
                    {plan.price !== "Free" && <span className="text-gray-600 ml-1">/{plan.period}</span>}
                  </div>
                  <p className="text-gray-600">{plan.description}</p>
                </div>
                <ul className="space-y-3 mb-8">
                  {plan.features.map((feature, featureIndex) => (
                    <li key={featureIndex} className="flex items-center">
                      <CheckCircle className="h-5 w-5 text-green-500 mr-3 flex-shrink-0" />
                      <span className="text-gray-600">{feature}</span>
                    </li>
                  ))}
                </ul>
                <button className={`w-full py-3 px-4 rounded-lg font-medium transition-colors ${
                  plan.popular 
                    ? 'bg-primary-600 text-white hover:bg-primary-700' 
                    : 'bg-gray-100 text-gray-900 hover:bg-gray-200'
                }`}>
                  {plan.cta}
                </button>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Testimonials Section */}
      <section id="testimonials" className="py-20 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
              Trusted by Thousands of Americans
            </h2>
            <p className="text-xl text-gray-600">
              See what our users say about FinanceFlow
            </p>
          </div>
          
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {[
              {
                name: "Sarah Johnson",
                role: "Marketing Manager",
                location: "Austin, TX",
                content: "FinanceFlow helped me save $15,000 for my house down payment in just 18 months. The goal tracking feature is incredible!",
                rating: 5
              },
              {
                name: "Mike Chen",
                role: "Software Engineer",
                location: "San Francisco, CA",
                content: "Finally, a budgeting app that actually works. The expense categorization is spot-on and saves me hours every month.",
                rating: 5
              },
              {
                name: "Emily Rodriguez",
                role: "Teacher",
                location: "Miami, FL",
                content: "As a teacher on a tight budget, FinanceFlow helps me make every dollar count. The insights are game-changing.",
                rating: 5
              }
            ].map((testimonial, index) => (
              <div key={index} className="card">
                <div className="flex items-center mb-4">
                  {[...Array(testimonial.rating)].map((_, i) => (
                    <Star key={i} className="h-5 w-5 text-yellow-400 fill-current" />
                  ))}
                </div>
                <p className="text-gray-600 mb-6 italic">"{testimonial.content}"</p>
                <div className="flex items-center">
                  <div className="w-12 h-12 bg-primary-100 rounded-full flex items-center justify-center">
                    <span className="text-primary-600 font-semibold">
                      {testimonial.name.split(' ').map(n => n[0]).join('')}
                    </span>
                  </div>
                  <div className="ml-4">
                    <p className="font-semibold text-gray-900">{testimonial.name}</p>
                    <p className="text-sm text-gray-600">{testimonial.role} • {testimonial.location}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-20 bg-primary-600">
        <div className="max-w-4xl mx-auto text-center px-4 sm:px-6 lg:px-8">
          <h2 className="text-3xl md:text-4xl font-bold text-white mb-6">
            Ready to Transform Your Financial Life?
          </h2>
          <p className="text-xl text-primary-100 mb-8">
            Join thousands of Americans who are already building wealth with FinanceFlow
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Link to="/register" className="bg-white text-primary-600 hover:bg-gray-100 font-semibold py-4 px-8 rounded-lg transition-colors text-lg">
              Start Your Free Trial
            </Link>
            <button className="border-2 border-white text-white hover:bg-white hover:text-primary-600 font-semibold py-4 px-8 rounded-lg transition-colors text-lg">
              Schedule Demo
            </button>
          </div>
          <p className="text-primary-200 text-sm mt-6">
            30-day free trial • No credit card required • Cancel anytime
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-gray-900 text-white py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
            <div>
              <div className="flex items-center mb-4">
                <Wallet className="h-8 w-8 text-primary-400" />
                <span className="ml-2 text-xl font-bold">FinanceFlow</span>
              </div>
              <p className="text-gray-400">
                Empowering Americans to achieve financial freedom through smart money management.
              </p>
            </div>
            <div>
              <h3 className="font-semibold mb-4">Product</h3>
              <ul className="space-y-2 text-gray-400">
                <li><a href="#" className="hover:text-white transition-colors">Features</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Pricing</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Mobile App</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Security</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-semibold mb-4">Company</h3>
              <ul className="space-y-2 text-gray-400">
                <li><a href="#" className="hover:text-white transition-colors">About Us</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Careers</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Press</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Contact</a></li>
              </ul>
            </div>
            <div>
              <h3 className="font-semibold mb-4">Support</h3>
              <ul className="space-y-2 text-gray-400">
                <li><a href="#" className="hover:text-white transition-colors">Help Center</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Privacy Policy</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Terms of Service</a></li>
                <li><a href="#" className="hover:text-white transition-colors">API Docs</a></li>
              </ul>
            </div>
          </div>
          <div className="border-t border-gray-800 mt-8 pt-8 text-center text-gray-400">
            <p>&copy; 2024 FinanceFlow. All rights reserved. Made with ❤️ in the USA.</p>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;