SET SQL_SAFE_UPDATES = 0;
drop database if exists dbcarros;

create database dbcarros;
use dbcarros;

CREATE TABLE montadora (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  paisfundacao VARCHAR(255) NOT NULL,
  nomepresidente VARCHAR(255) NOT NULL,
  datafundacao DATE NOT NULL
);

CREATE TABLE carro (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  modelo VARCHAR(255) NOT NULL,
  placa VARCHAR(20) NOT NULL,
  idmontadora INT NOT NULL,
  ano INT NOT NULL,
  valor DOUBLE NOT NULL,
  FOREIGN KEY (idmontadora) REFERENCES montadora(id)
);

INSERT INTO carro (modelo, placa, idmontadora, ano, valor)
VALUES
  ('Fiesta', 'DEF-5678', 2, 2021, 60000.00),
  ('Onix', 'GHI-9012', 3, 2022, 70000.00),
  ('Mobi', 'JKL-3456', 4, 2023, 40000.00);

INSERT INTO montadora (nome, paisfundacao, nomepresidente, datafundacao)
VALUES
  ('Fiat', 'Itália', 'Olivier François', '1899-07-11'),
  ('Ford', 'Estados Unidos', 'Jim Farley', '1903-06-16'),
  ('Chevrolet', 'Estados Unidos', 'Mary Barra', '1911-11-03');

SELECT * FROM carro;
SELECT * FROM montadora;

