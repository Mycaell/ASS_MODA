
CREATE TABLE bandeira
(
    id serial NOT NULL,
    nome character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cliente
(
    id serial NOT NULL,
    cpf character varying(14) NOT NULL,
    nome character varying NOT NULL,
    sobrenome character varying  NOT NULL,
    sexo character varying  NOT NULL,
    telefone character varying(15)  NOT NULL,
    data_nascimento date NOT NULL,
    id_funcionario integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE endereco
(
    id serial NOT NULL ,
    bairro character varying,
    rua character varying  NOT NULL,
    numero integer NOT NULL, 
    PRIMARY KEY (id)
);

CREATE TABLE cliente_endereco
(
   id_cliente integer NOT NULL,

    id_endereco integer NOT NULL,

    PRIMARY KEY (id_cliente, id_endereco),

    FOREIGN KEY (id_cliente) REFERENCES cliente (id) on delete cascade,

    FOREIGN KEY (id_endereco) REFERENCES endereco (id) on delete cascade

);

CREATE TABLE funcionario
(
    id serial NOT NULL,
    nome character varying NOT NULL,
    sobrenome character varying NOT NULL,
    sexo character varying NOT NULL,
    cpf character varying(14) NOT NULL,
    telefone character varying(15) NOT NULL,
    data_nascimento date NOT NULL,
    login character varying NOT NULL,
    senha character varying NOT NULL,
    cargo character varying NOT NULL,
    PRIMARY KEY (id)
);


INSERT INTO funcionario(
	nome, sobrenome, sexo, cpf, telefone, data_nascimento, login, senha, cargo)
	VALUES ('root', 'root', 'R', 'root', 'root', current_date, 'root', 'root', 'root');

CREATE TABLE funcionario_endereco
(
id_funcionario integer NOT NULL,

    id_endereco integer NOT NULL,

    PRIMARY KEY (id_funcionario, id_endereco),

    FOREIGN KEY (id_endereco) REFERENCES endereco (id) on delete cascade,

    FOREIGN KEY (id_funcionario) REFERENCES funcionario (id) on delete cascade

);



CREATE TABLE pagamento
(
    
    id serial NOT NULL,

    tipo integer NOT NULL,

    qtd_parcelas integer,

    data_pagamento date NOT NULL,

    hora_pagamento time without time zone NOT NULL,

    num_cartao character varying(19),

    tipo_de_cartao character varying(30),

    id_bandeira integer,
    PRIMARY KEY (id),

    FOREIGN KEY (id_bandeira) REFERENCES bandeira (id) on delete cascade

);




CREATE TABLE produto
(
    id serial NOT NULL,
    nome character varying NOT NULL,
    preco real NOT NULL,
    categoria character varying NOT NULL,
    quant_estoque integer NOT NULL,
    cor character varying  NOT NULL,
    tamanho character varying NOT NULL,
    marca character varying NOT NULL,
    descricao character varying NOT NULL,
    id_funcionario integer NOT NULL,
    PRIMARY KEY (id)
);




CREATE TABLE venda
(
    
    num_venda serial NOT NULL,

    valor real NOT NULL,	
    data_venda timestamp without time zone,

    id_cliente integer NOT NULL,

    id_funcionario integer NOT NULL,

    id_pagamento integer NOT NULL,

    PRIMARY KEY (num_venda),

    FOREIGN KEY (id_cliente) REFERENCES cliente (id) on delete cascade,

    FOREIGN KEY (id_funcionario) REFERENCES funcionario (id) on delete cascade,

    FOREIGN KEY (id_pagamento) REFERENCES pagamento (id) on delete cascade

);


CREATE TABLE venda_produto
(
    num_venda integer NOT NULL,
    id_produto integer NOT NULL,
    quant_produto integer NOT NULL,
    PRIMARY KEY (num_venda, id_produto),
    FOREIGN KEY (id_produto) REFERENCES produto (id) ,
    FOREIGN KEY (num_venda) REFERENCES venda (num_venda) 
);


CREATE TABLE cores_produtos
(

    id serial NOT NULL,
    nome character varying  NOT NULL,

    PRIMARY KEY (id)

);

CREATE TABLE categoria_produtos
(

    id serial NOT NULL ,
    categoria character varying  NOT NULL,

    PRIMARY KEY (id)

);

CREATE TABLE tamanhos_produtos
(
 
   id serial NOT NULL ,
   tamanho character varying  NOT NULL,

   PRIMARY KEY (id)

);




