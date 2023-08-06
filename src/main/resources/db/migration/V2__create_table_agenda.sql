create table agenda (
	id bigint auto_increment primary key,
    decricao varchar(255),
    data_hora timestamp,
    data_criacao timestamp,
    paciente_id bigint,
    foreign key (paciente_id) references paciente(id)
);