CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(10) NOT NULL DEFAULT 'user',
                       criated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE banks (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(30) NOT NULL
);


CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          bank_id BIGINT NOT NULL,
                          name VARCHAR(120) NOT NULL,
                          `type` varchar(30) NOT NULL DEFAULT 'corrente',
                          balance DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                          created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (bank_id) REFERENCES banks(id) ON DELETE CASCADE
);

CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              account_id BIGINT NOT NULL,
                              category VARCHAR(30) DEFAULT NULL,
                              name VARCHAR(50) NULL,
                              `type` VARCHAR(30) NOT NULL,
                              amount DECIMAL(10,2) NOT NULL,
                              created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                              FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE,

);
