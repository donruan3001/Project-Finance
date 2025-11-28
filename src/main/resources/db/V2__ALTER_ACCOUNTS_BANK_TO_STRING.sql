-- Migration: Alterar bank_id para bank (String) na tabela accounts
-- Remove a foreign key e substitui por uma coluna VARCHAR nullable

-- Passo 1: Encontrar e remover foreign key (se existir)
SET @fk_name = NULL;
SELECT CONSTRAINT_NAME INTO @fk_name
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'accounts'
  AND COLUMN_NAME = 'bank_id'
  AND REFERENCED_TABLE_NAME IS NOT NULL
LIMIT 1;

SET @drop_fk = IF(@fk_name IS NOT NULL,
    CONCAT('ALTER TABLE accounts DROP FOREIGN KEY ', @fk_name),
    'SELECT 1');
PREPARE stmt FROM @drop_fk;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Passo 2: Adicionar coluna bank se não existir
SET @col_exists = 0;
SELECT COUNT(*) INTO @col_exists
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'accounts'
  AND COLUMN_NAME = 'bank';

SET @add_col = IF(@col_exists = 0,
    'ALTER TABLE accounts ADD COLUMN bank VARCHAR(100) NULL AFTER user_id',
    'SELECT 1');
PREPARE stmt FROM @add_col;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Passo 3: Migrar dados existentes (se houver)
UPDATE accounts a
INNER JOIN banks b ON a.bank_id = b.id
SET a.bank = b.name
WHERE a.bank IS NULL AND a.bank_id IS NOT NULL;

-- Passo 4: Remover coluna bank_id se existir
SET @col_exists = 0;
SELECT COUNT(*) INTO @col_exists
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'accounts'
  AND COLUMN_NAME = 'bank_id';

SET @drop_col = IF(@col_exists > 0,
    'ALTER TABLE accounts DROP COLUMN bank_id',
    'SELECT 1');
PREPARE stmt FROM @drop_col;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

