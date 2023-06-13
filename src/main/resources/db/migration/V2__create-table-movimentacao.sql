CREATE TABLE movimentacao(
    id bigint not null auto_increment,
    container_id bigint not null,
    tipo_movimentacao varchar(25) not null,
    data_inicio datetime not null,
    data_fim datetime not null,
    primary key(id),
    constraint fk_movimentacao_container_id foreign key(container_id) references container(id)
);