CREATE TABLE container(
    id bigint not null auto_increment,
    cliente varchar(150) not null,
    numero_container varchar(15) unique not null,
    tipo varchar(20) not null,
    status varchar(20) not null,
    categoria varchar(20) not null,
    primary key(id)
);