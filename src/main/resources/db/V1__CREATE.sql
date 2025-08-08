
CREATE TABLE usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome TEXT NOT NULL,
  email TEXT UNIQUE NOT NULL,
  senha_hash TEXT NOT NULL
);

CREATE TABLE bancos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome TEXT NOT NULL,
  usuario_id INT NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);


CREATE TABLE categorias (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome TEXT NOT NULL
);

INSERT INTO categorias (nome) VALUES
('Alimentação'), ('Transporte'), ('Lazer'), ('Educação'), ('Saúde'), ('Outros');


CREATE TABLE gastos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  descricao TEXT NOT NULL,
  valor DECIMAL(10, 2) NOT NULL,
  data DATE NOT NULL,
  categoria_id INT NOT NULL,
  banco_id INT NOT NULL,
  usuario_id INT NOT NULL,
  FOREIGN KEY (categoria_id) REFERENCES categorias(id),
  FOREIGN KEY (banco_id) REFERENCES bancos(id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
