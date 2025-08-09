ALTER TABLE bancos(
REMOVE COLUMN usuario_id);



CREATE TABLE contas(
id INT AUTO_INCREMENT PRIMARY KEY ,
usuario_id INT NOT NULL,
banco_id INT NOT NULL,
nome TEXT(120) NOT NULL,
tipo ENUM('corrente', 'poupanca', 'investimento','outro')DEFAULT 'corrente' NOT NULL,
saldo DECIMAL(10, 2) NOT NULL DEFAULT 0.00,'
criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
FOREIGN KEY (banco_id) REFERENCES bancos(id) ON DELETE CASCADE

)