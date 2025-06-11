-- Active: 1749669442624@@127.0.0.1@5432@petagenda

-- Criando Tabela Cliente
CREATE TABLE cliente(

    idcliente SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(10),
    email VARCHAR(255),
    senha VARCHAR(255)

);

-- Criando Tabela Admin 

CREATE TABLE admin(

    idadmin SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR (255), 
    telefone VARCHAR(20),
    senha VARCHAR(255)
);

-- DROP TABLE admin;

-- Criando tabelasolicitação agendamento
CREATE TABLE soliagenda(
    idsoli SERIAL PRIMARY KEY,
    idcliente INT NOT NULL,
    dtsoli DATE DEFAULT NOW(),
    idadmin INT,
    confirmsoli BOOLEAN,
    FOREIGN KEY (idadmin) REFERENCES admin(idadmin),
    FOREIGN KEY (idcliente) REFERENCES cliente(idcliente)
);

-- Criando tabela agenda
CREATE TABLE agenda(

    idagenda SERIAL PRIMARY KEY,
    idsoli INT,
    diasemana VARCHAR(15),
    dthagenda TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY(idsoli) REFERENCES soliagenda(idsoli)

);

-- DROP TABLE soliagenda;
-- DROP TABLE agenda;