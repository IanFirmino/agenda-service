create table paciente (
	id bigint auto_increment primary key,
    nome varchar(50),
    sobrenome varchar(100),
    cpf varchar(15),
    email varchar(100)
);