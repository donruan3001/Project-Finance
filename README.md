# 💰 Project Finance

Sistema de gestão financeira pessoal desenvolvido com arquitetura full-stack, permitindo que usuários gerenciem suas contas bancárias, bancos e transações financeiras de forma organizada e segura.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Documentação da API](#documentação-da-api)
- [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)

## 🎯 Sobre o Projeto

O **Project Finance** é uma aplicação web completa para controle financeiro pessoal, desenvolvida com foco em segurança, usabilidade e organização. O sistema permite que usuários:

- Criem e gerenciem múltiplas contas bancárias
- Cadastrem bancos
- Registrem transações financeiras (receitas e despesas)
- Acompanhem seus saldos e movimentações
- Tenham acesso seguro através de autenticação JWT

## 🛠️ Tecnologias

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **JWT (JSON Web Token)** - Autenticação stateless
- **MySQL 8.0** - Banco de dados relacional
- **Flyway** - Migração de banco de dados
- **Lombok** - Redução de boilerplate
- **Swagger/OpenAPI** - Documentação da API
- **Maven** - Gerenciamento de dependências

### Frontend
- **Angular 14** - Framework frontend
- **TypeScript** - Linguagem de programação
- **RxJS** - Programação reativa
- **Angular Router** - Roteamento

### DevOps
- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers

## ✨ Funcionalidades

### Autenticação
- ✅ Registro de novos usuários
- ✅ Login com JWT
- ✅ Controle de acesso baseado em roles (USER/ADMIN)

### Gestão de Bancos
- ✅ Cadastro de bancos
- ✅ Listagem de bancos cadastrados

### Gestão de Contas
- ✅ Criação de contas bancárias vinculadas a usuários
- ✅ Listagem de contas do usuário
- ✅ Controle de saldo por conta
- ✅ Tipos de conta (corrente, poupança, etc.)

### Gestão de Transações
- ✅ Registro de transações (receitas e despesas)
- ✅ Categorização de transações
- ✅ Histórico de movimentações
- ✅ Atualização automática de saldos

## 📁 Estrutura do Projeto

```
Project-Finance/
├── src/                          # Código fonte do backend
│   └── main/
│       ├── java/finance/
│       │   ├── config/           # Configurações (JWT, CORS, Security, Swagger)
│       │   ├── controlers/      # Controllers REST
│       │   ├── domain/          # Entidades JPA
│       │   │   ├── acounts/    # Entidade Account
│       │   │   ├── banks/       # Entidade Bank
│       │   │   ├── transactions/# Entidade Transaction
│       │   │   └── user/        # Entidade User
│       │   ├── dto/             # Data Transfer Objects
│       │   ├── exceptions/      # Tratamento de exceções
│       │   ├── repository/      # Repositórios JPA
│       │   ├── services/        # Lógica de negócio
│       │   └── validator/      # Validadores customizados
│       └── resources/
│           ├── application.properties
│           └── db/              # Scripts de migração Flyway
│               └── V1__CREATE.sql
├── front/                        # Aplicação Angular
│   └── src/
│       └── app/
│           ├── core/           # Serviços core (auth, account, bank, transaction)
│           ├── features/        # Módulos de funcionalidades
│           │   ├── accounts/   # Módulo de contas
│           │   ├── auth/       # Módulo de autenticação
│           │   ├── banks/      # Módulo de bancos
│           │   └── transactions/# Módulo de transações
│           └── shared/         # Componentes compartilhados
├── docker-compose.yaml          # Configuração Docker Compose
├── Dockerfile                   # Imagem Docker do backend
└── pom.xml                      # Configuração Maven
```

## 📋 Pré-requisitos

Antes de começar, você precisa ter instalado em sua máquina:

- **Java 17** ou superior
- **Maven 3.6+**
- **Node.js 14+** e **npm**
- **Docker** e **Docker Compose** (opcional, para execução via containers)
- **MySQL 8.0** (se executar localmente sem Docker)

## 🚀 Instalação e Execução

### Opção 1: Execução com Docker Compose (Recomendado)

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd Project-Finance
```

2. Execute o Docker Compose:
```bash
docker-compose up -d
```

Isso irá:
- Criar e iniciar o container MySQL na porta 3307
- Criar e iniciar o container da aplicação Spring Boot na porta 8080

3. Para parar os containers:
```bash
docker-compose down
```

### Opção 2: Execução Local

#### Backend

1. Configure o banco de dados MySQL:
   - Crie um banco de dados chamado `finance`
   - Configure as credenciais no arquivo `src/main/resources/application.properties`

2. Execute a aplicação Spring Boot:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8090`

#### Frontend

1. Navegue até a pasta do frontend:
```bash
cd front
```

2. Instale as dependências:
```bash
npm install
```

3. Inicie o servidor de desenvolvimento:
```bash
npm start
```

A aplicação estará disponível em `http://localhost:4200`

## 📚 Documentação da API

A documentação interativa da API está disponível através do Swagger UI:

**URL:** `http://localhost:8080/swagger-ui/index.html`

Ou acesse a documentação em formato JSON:
**URL:** `http://localhost:8080/v3/api-docs`

### Principais Endpoints

- **POST** `/auth/register` - Registro de novo usuário
- **POST** `/auth/login` - Autenticação e obtenção de token JWT
- **GET/POST** `/accounts` - Listagem e criação de contas
- **GET/POST** `/banks` - Listagem e criação de bancos
- **GET/POST** `/transactions` - Listagem e criação de transações

## 🗄️ Estrutura do Banco de Dados

O banco de dados possui as seguintes tabelas:

### `users`
- Armazena informações dos usuários do sistema
- Campos: id, name, username, password, role, criated, updated

### `banks`
- Armazena os bancos cadastrados
- Campos: id, name

### `accounts`
- Armazena as contas bancárias dos usuários
- Campos: id, user_id, bank_id, name, type, balance, created
- Relacionamentos: Foreign Key com `users` e `banks`

### `transactions`
- Armazena as transações financeiras
- Campos: id, account_id, category, name, type, amount, created, updated
- Relacionamento: Foreign Key com `accounts`

## 🔐 Segurança

- Autenticação baseada em JWT (JSON Web Token)
- Senhas criptografadas (Spring Security)
- Controle de acesso baseado em roles
- Configuração CORS para comunicação frontend-backend
- Validação de dados de entrada

## 🧪 Testes

Para executar os testes do backend:

```bash
mvn test
```

Para executar os testes do frontend:

```bash
cd front
npm test
```

## 📝 Notas

- A porta padrão do backend é **8090** quando executado localmente
- A porta padrão do backend é **8080** quando executado via Docker
- O banco de dados MySQL está configurado para a porta **3307** no Docker Compose
- O secret JWT está configurado no `application.properties` como `JWT_SECRET=your-256-bit-secret`

## 👥 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## 📄 Licença

Este projeto está sob licença livre para uso pessoal e educacional.

---

Desenvolvido com ❤️ para facilitar o controle financeiro pessoal.
