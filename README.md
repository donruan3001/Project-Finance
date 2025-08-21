# Project-Finance

### 📋 Visão Geral
API REST completa para gestão financeira pessoal com recursos avançados de orçamento, metas, analytics e notificações.
## 🚀 Finance API - Sistema Completo de Gestão Financeira
### 🛠️ Tecnologias
- **Backend**: Spring Boot 3.5.4, Java 17
- **Banco de Dados**: MySQL 8.0
- **Segurança**: JWT, Spring Security
- **Documentação**: OpenAPI 3 (Swagger)
- **Migração**: Flyway
### 🔗 Endpoints Principais
#### 🔐 Autenticação
- `POST /auth/register` - Registro de usuário
- `POST /auth/login` - Login e obtenção do JWT
#### 👤 Usuários
- `GET /users/{userId}/profile` - Perfil do usuário
- `PUT /users/{userId}/profile` - Atualizar perfil
- `DELETE /users/{userId}` - Deletar usuário
#### 🏦 Bancos
- `POST /banks` - Criar banco
- `GET /banks` - Listar bancos
- `PUT /banks/{id}` - Atualizar banco
- `DELETE /banks/{id}` - Deletar banco
#### 💳 Contas
- `POST /accounts` - Criar conta
- `GET /accounts` - Listar contas
- `PATCH /accounts/{id}` - Atualizar conta
- `DELETE /accounts/{id}` - Deletar conta
#### 💰 Transações
- `POST /transactions` - Criar transação
- `GET /transactions/user/{userId}` - Listar transações do usuário
- `GET /transactions/user/{userId}/recent` - Transações recentes
- `DELETE /transactions/{id}/user/{userId}` - Deletar transação
#### 🏷️ Categorias
- `POST /categories` - Criar categoria
- `GET /categories` - Listar categorias
- `GET /categories/type/{type}` - Categorias por tipo
- `DELETE /categories/{id}` - Deletar categoria
#### 💼 Orçamentos
- `POST /budgets` - Criar orçamento
- `GET /budgets/user/{userId}` - Orçamentos do usuário
- `GET /budgets/user/{userId}/active` - Orçamentos ativos
#### 🎯 Metas
- `POST /goals` - Criar meta
- `GET /goals/user/{userId}` - Metas do usuário
- `GET /goals/user/{userId}/active` - Metas ativas
- `PATCH /goals/{goalId}/progress` - Atualizar progresso
#### 📊 Dashboard
- `GET /dashboard/user/{userId}` - Dashboard completo
#### 📈 Analytics
- `GET /analytics/user/{userId}` - Analytics personalizado
- `GET /analytics/user/{userId}/current-month` - Analytics do mês atual
#### 🔔 Notificações
- `GET /notifications/user/{userId}` - Todas as notificações
- `GET /notifications/user/{userId}/unread` - Não lidas
- `GET /notifications/user/{userId}/count` - Contador
- `PATCH /notifications/{id}/read` - Marcar como lida
- `PATCH /notifications/user/{userId}/read-all` - Marcar todas como lidas
### 🎨 Recursos para Apps Modernos
#### 📱 Mobile-First Design
- APIs otimizadas para consumo mobile
- Paginação eficiente
- Responses compactos
- Suporte a filtros avançados
#### 🎯 Dashboard Inteligente
- Resumo financeiro em tempo real
- Gráficos de receitas vs despesas
- Progresso de metas e orçamentos
- Transações recentes
- Análise por categorias
#### 📊 Analytics Avançados
- Comparação mensal
- Tendências por categoria
- Taxa de economia
- Performance de orçamentos
- Insights personalizados
#### 🔔 Sistema de Notificações
- Alertas de orçamento excedido
- Lembretes de metas
- Notificações de transações grandes
- Alertas de segurança
### 🚀 Como Executar
1. **Clone o repositório**
```bash
git clone <repository-url>
cd finance-api
```
2. **Configure o banco MySQL**
```sql
CREATE DATABASE finance;
```
3. **Configure as variáveis de ambiente**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance
spring.datasource.username=root
spring.datasource.password=sua_senha
JWT_SECRET=seu_jwt_secret_256_bits
```
4. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```
### 📚 Documentação
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs
### 🧪 Testes
Execute o script de testes automatizados:
```bash
chmod +x test-curl.sh
./test-curl.sh
```
### 🎨 Design System para Apps
#### 🎨 Cores Sugeridas
- **Primary**: #4ECDC4 (Teal)
- **Secondary**: #45B7D1 (Blue)
- **Success**: #96CEB4 (Green)
- **Warning**: #FFEAA7 (Yellow)
- **Error**: #FF6B6B (Red)
- **Income**: #98D8C8 (Light Green)
- **Expense**: #FFB3BA (Light Red)
#### 📱 Componentes Recomendados
- Cards com sombras suaves
- Gráficos interativos (Chart.js, D3.js)
- Animações de transição
- Pull-to-refresh
- Skeleton loading
- Bottom sheets para ações
- Floating action buttons
#### 🎯 UX/UI Guidelines
- **Navegação**: Tab bar com 5 seções principais
- **Dashboard**: Cards informativos com quick actions
- **Transações**: Lista com categorização visual
- **Analytics**: Gráficos interativos e filtros
- **Perfil**: Configurações e preferências
### 🔒 Segurança
- JWT com expiração de 24h
- Validação de ownership em todas as operações
- Criptografia de senhas com BCrypt
- Validação de entrada em todos os endpoints
- Rate limiting (recomendado para produção)
### 🚀 Próximos Passos
1. **Implementar cache Redis**
2. **Adicionar rate limiting**
3. **Implementar webhooks**
4. **Adicionar relatórios em PDF**
5. **Integração com bancos (Open Banking)**
6. **Push notifications**
7. **Backup automático**
### 📞 Suporte
Para dúvidas ou sugestões, abra uma issue no repositório.