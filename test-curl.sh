#!/bin/bash

# Finance API Test Script
# Execute este script para testar a API automaticamente

BASE_URL="http://localhost:8080"

echo "=== TESTANDO API FINANCE ==="

# 1. Registrar usuário
echo "1. Registrando usuário..."
curl -X POST "$BASE_URL/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@email.com", 
    "password": "123456",
    "role": "USER"
  }' \
  -w "\nStatus: %{http_code}\n\n"

# 2. Fazer login e capturar token
echo "2. Fazendo login..."
LOGIN_RESPONSE=$(curl -X POST "$BASE_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "password": "123456"
  }' \
  -w "\nStatus: %{http_code}\n" \
  -s)

echo "$LOGIN_RESPONSE"

# Extrair token (assumindo que retorna {"token": "..."})
TOKEN=$(echo "$LOGIN_RESPONSE" | grep -o '"token":"[^"]*"' | cut -d'"' -f4)
echo "Token capturado: ${TOKEN:0:50}..."

if [ -z "$TOKEN" ]; then
  echo "ERRO: Token não foi capturado. Verifique o login."
  exit 1
fi

# 3. Criar banco
echo "3. Criando banco..."
curl -X POST "$BASE_URL/banks" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Banco do Brasil",
    "code": "001"
  }' \
  -w "\nStatus: %{http_code}\n\n"

# 4. Criar conta
echo "4. Criando conta..."
curl -X POST "$BASE_URL/accounts" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "userId": 1,
    "bankId": 1,
    "name": "Conta Corrente Principal",
    "type": "CHECKING",
    "balance": 1000.00
  }' \
  -w "\nStatus: %{http_code}\n\n"

# 5. Listar contas
echo "5. Listando contas..."
curl -X GET "$BASE_URL/accounts" \
  -H "Authorization: Bearer $TOKEN" \
  -w "\nStatus: %{http_code}\n\n"

# 6. Criar transação de receita
echo "6. Criando transação de receita..."
curl -X POST "$BASE_URL/transactions" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "userId": 1,
    "accountId": 1,
    "name": "Salário",
    "category": "SALARY",
    "type": "INCOME",
    "amount": 3000.00
  }' \
  -w "\nStatus: %{http_code}\n\n"

# 7. Criar transação de despesa
echo "7. Criando transação de despesa..."
curl -X POST "$BASE_URL/transactions" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "userId": 1,
    "accountId": 1,
    "name": "Compra no supermercado",
    "category": "FOOD",
    "type": "EXPENSE",
    "amount": 150.50
  }' \
  -w "\nStatus: %{http_code}\n\n"

echo "=== TESTES CONCLUÍDOS ==="
echo "Acesse http://localhost:8080/swagger-ui.html para documentação interativa" 