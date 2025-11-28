# 🔧 Como Reparar a Migration do Flyway

Se você está vendo o erro "Detected failed migration to version 2", siga estes passos:

## ⚡ Solução Rápida (Recomendado)

### Via Docker:

```bash
# 1. Conectar ao container MySQL
docker exec -it db_mysql mysql -uroot -pmidea finance

# 2. Executar o script de reparo
DELETE FROM flyway_schema_history WHERE version = '2' AND success = 0;

# 3. Verificar estado
SELECT version, description, success, installed_on 
FROM flyway_schema_history 
ORDER BY installed_rank DESC;

# 4. Sair do MySQL
exit

# 5. Reiniciar a aplicação
docker-compose restart app
```

### Via MySQL Local:

```sql
USE finance;

-- Remover registro de migration falhada
DELETE FROM flyway_schema_history WHERE version = '2' AND success = 0;

-- Verificar estado atual
SELECT version, description, type, success, installed_on 
FROM flyway_schema_history 
ORDER BY installed_rank;

-- Verificar estrutura da tabela accounts
DESCRIBE accounts;
```

## Opção 1: Reparar Manualmente

Conecte-se ao banco de dados MySQL e execute:

```sql
-- 1. Verificar o estado das migrations
SELECT * FROM flyway_schema_history ORDER BY installed_rank DESC;

-- 2. Se a migration V2 está marcada como FAILED, remova o registro
DELETE FROM flyway_schema_history WHERE version = '2' AND success = 0;

-- 3. Verificar se a coluna bank já existe
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE 
FROM information_schema.COLUMNS 
WHERE TABLE_SCHEMA = 'finance' 
AND TABLE_NAME = 'accounts' 
AND COLUMN_NAME IN ('bank', 'bank_id');

-- 4. A migration V2 foi corrigida e será re-executada automaticamente
-- Ela é idempotente (pode ser executada múltiplas vezes sem problemas)
```

## Opção 2: Via Docker (Se estiver usando Docker)

```bash
# 1. Parar os containers
docker-compose down

# 2. Conectar ao banco MySQL
docker exec -it db_mysql mysql -uroot -pmidea finance

# 3. Executar os comandos SQL acima

# 4. Reiniciar os containers
docker-compose up -d
```

## Opção 3: Limpar e Recriar (Apenas para Desenvolvimento)

⚠️ **ATENÇÃO: Isso apagará todos os dados!**

```bash
# 1. Parar containers
docker-compose down

# 2. Remover volume do MySQL
docker volume rm project-finance_mysql_data

# 3. Reiniciar (criará banco limpo)
docker-compose up -d
```

## Verificação

Após reparar, verifique se está tudo correto:

```sql
-- Verificar estrutura da tabela accounts
DESCRIBE accounts;

-- Verificar migrations aplicadas
SELECT version, description, success, installed_on 
FROM flyway_schema_history 
ORDER BY installed_rank;
```

A tabela `accounts` deve ter:
- ✅ Coluna `bank` (VARCHAR(100), nullable)
- ❌ Sem coluna `bank_id`
- ❌ Sem foreign key para `banks`

