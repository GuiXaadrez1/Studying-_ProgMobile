-- Active: 1749757574490@@127.0.0.1@5432@petagenda

-- Alimentando a Tabela Clientes
INSERT INTO cliente (nome, telefone, email, senha) VALUES
('Ana Costa', '1198765432', 'ana@gmail.com', 'senha123'),
('Bruno Silva', '1191234567', 'bruno@gmail.com', 'senha456'),
('Carlos Souza', '11933334444', 'carlos@gmail.com', 'senha789'),
('Daniela Lima', '11944445555', 'daniela@gmail.com', 'senha321'),
('Eduardo Rocha', '11955556666', 'eduardo@gmail.com', 'senha654'),
('Fernanda Torres', '11966667777', 'fernanda@gmail.com', 'senha987'),
('Gabriel Alves', '11977778888', 'gabriel@gmail.com', 'senha111'),
('Helena Ribeiro', '11988889999', 'helena@gmail.com', 'senha222'),
('Igor Mendes', '11999990000', 'igor@gmail.com', 'senha333'),
('Juliana Teixeira', '11888887777', 'juliana@gmail.com', 'senha444');


-- INSERINDO DADOS NA TABELA ADMIN

INSERT INTO admin (nome, email, telefone, senha) VALUES

('Guilherme Henrique', 'GuiPetShop@admin.com', '11888887777', md5('123456'));

-- Alimentando a Tabela
INSERT INTO soliagenda (idcliente, idadmin, confirmsoli, descricao) VALUES
(1, 1, TRUE, NULL),
(2, 1, FALSE, 'Agendamento recusado pelo sistema'),
(3, 1, NULL, NULL),
(4, 1, TRUE, NULL),
(5, 1, FALSE, 'Dados incompletos no cadastro'),
(6, 1, NULL, NULL),
(7, 1, TRUE, NULL),
(8, 1, FALSE, 'Horário não disponível'),
(9, 1, NULL, NULL),
(10, 1, TRUE, NULL);
-- Alimentando tabela Agenda

INSERT INTO agenda (idsoli, diasemana, dthagenda) VALUES
(1, 'Segunda', '2025-06-17 10:00:00'),
(2, 'Terça', '2025-06-18 11:00:00'),
(3, 'Quarta', '2025-06-19 09:30:00'),
(4, 'Quinta', '2025-06-20 14:00:00'),
(5, 'Sexta', '2025-06-21 13:00:00'),
(6, 'Segunda', '2025-06-24 10:00:00'),
(7, 'Terça', '2025-06-25 11:30:00'),
(8, 'Quarta', '2025-06-26 09:00:00'),
(9, 'Quinta', '2025-06-27 15:00:00'),
(10, 'Sexta', '2025-06-28 12:00:00');


-- UPDATES

UPDATE public.soliagenda 
    SET descricao = 'Horário não disponível'
        WHERE idsoli = 2;

UPDATE public.soliagenda 
    SET descricao = 'Horário não disponível'
        WHERE idsoli = 5;



