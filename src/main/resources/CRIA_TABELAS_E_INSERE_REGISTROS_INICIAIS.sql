CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('José Adailton'), ('Elizangela Neres'), ('Luiz Sobrinho'), ('Rodrigo Porto');

CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE agendamento (rowid bigint auto_increment, cd_funcionario bigint, cd_exame bigint, data_agendamento date);
INSERT INTO agendamento (cd_funcionario, cd_exame, data_agendamento) VALUES (2, 3, '2023-12-12'), (3, 1, '2023-12-12'), (4, 2, '2023-12-12'), (1, 2, '2023-12-12');

CREATE TABLE login (rowid bigint auto_increment, usuario VARCHAR(100), senha VARCHAR(100));
INSERT INTO login (usuario, senha) VALUES ('admin','admin');

