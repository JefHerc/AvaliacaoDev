CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('José Adailton'), ('Elizangela Neres'), ('Luiz Sobrinho'), ('Rodrigo Porto'), ('Marcos Assis'), ('Rodrigo Porto'), ('Rodrigo Porto'), ('Teodoro Leite'), ('Frederico Nakata'), ('Francisco Leitão'), ('Henrique Conceição'), ('Guilherme Cavalcanti');

CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Hemograma'), ('Colesterol'), ('Ureia e Creatinina'), ('Papanicolau'), ('Glicemia'), ('Transaminases (ALT e AST) ou TGP e TGO'),('Densitometria óssea');

CREATE TABLE agendamento (rowid bigint auto_increment, cd_funcionario bigint, cd_exame bigint, data_agendamento date);
INSERT INTO agendamento (cd_funcionario, cd_exame, data_agendamento) VALUES (2, 3, '2021-01-12'), (3, 1, '2021-12-12'), (4, 2, '2022-05-24'), (5, 2, '2022-07-07'), (8, 5, '2019-03-17'), (5, 6, '2019-08-15'), (12, 7, '2021-09-17'), (10, 8, '2023-02-22'), (11, 9, '2023-06-27'), (5, 10, '2023-12-12'), (10, 6, '2023-12-12');

CREATE TABLE login (rowid bigint auto_increment, usuario VARCHAR(100), senha VARCHAR(100));
INSERT INTO login (usuario, senha) VALUES ('admin','admin');

