/*
create table Clientes
(
    id int,
    nome TEXT,
    cnpj TEXT,
    constraint cli_pk primary key (id)
);

create table Usuarios (
                          id int,
                          id_cliente int,
                          email TEXT,
                          senha TEXT,
                          constraint user_fk foreign key (id_cliente) references Clientes(id),
                          constraint user_pk primary key (id, id_cliente)
);

CREATE TABLE CasasAluguel (
                              id INT,
                              id_cliente INT,
                              rua VARCHAR(100),
                              numero VARCHAR(10),
                              complemento VARCHAR(100),
                              cep VARCHAR(10),
                              cidade VARCHAR(50),
                              bairro VARCHAR(50),
                              numeroQuartos INT,
                              numeroBanheiros INT,
                              precoAluguel DECIMAL(10,2),
                              mobiliada BOOLEAN,
                              disponivel BOOLEAN,
                              dataDisponibilidade DATE,
                              descricao TEXT,
                              constraint casalu_fk foreign key (id_cliente) references Clientes(id),
                              constraint casalu_pk primary key (id)
);

create table ImagensAluguel(
                               id int,
                               casa_aluguel_id int,
                               url TEXT,
                               constraint img_alug_fk foreign key (casa_aluguel_id) references CasasAluguel(id),
                               constraint img_pk primary key (id, casa_aluguel_id)
);
*/