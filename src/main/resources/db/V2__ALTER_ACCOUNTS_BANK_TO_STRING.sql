-- Migration: Alterar bank_id para bank (String) na tabela accounts
-- Remove a foreign key e substitui por uma coluna VARCHAR nullable

-- Remover foreign key
ALTER TABLE accounts DROP FOREIGN KEY IF EXISTS accounts_ibfk_2;

-- Adicionar nova coluna bank (VARCHAR) como nullable
ALTER TABLE accounts ADD COLUMN bank VARCHAR(100) NULL AFTER user_id;

-- Migrar dados existentes (se houver)
-- Copia o nome do banco da tabela banks para a nova coluna bank
UPDATE accounts a
INNER JOIN banks b ON a.bank_id = b.id
SET a.bank = b.name;

-- Remover a coluna bank_id antiga
ALTER TABLE accounts DROP COLUMN bank_id;

-- Opcional: Remover a tabela banks se não for mais necessária
-- DROP TABLE IF EXISTS banks;

