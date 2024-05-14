/*
create table Cliente
(
    id int,
    nome TEXT,
    cnpj TEXT,
    constraint cli_pk primary key (id)
);

create table Usuario (
                          id int,
                          id_cliente int,
                          email TEXT,
                          senha TEXT,
                          constraint user_fk foreign key (id_cliente) references Cliente(id),
                          constraint user_pk primary key (id, id_cliente)
);

CREATE TABLE Imovel (
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
                              constraint casalu_fk foreign key (id_cliente) references Cliente(id),
                              constraint casalu_pk primary key (id)
);

create table ImagensImoveis(
                               id int,
                               casa_id int,
                               url TEXT,
                               constraint img_alug_fk foreign key (casa_id) references Imovel(id),
                               constraint img_pk primary key (id, casa_id)
);

 */
