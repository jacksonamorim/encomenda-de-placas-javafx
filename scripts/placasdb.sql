CREATE DATABASE placasdb;
USE placasdb;
CREATE TABLE clientes (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(80)
);

CREATE TABLE placas (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    data_encomenda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cor_placa VARCHAR(30) NOT NULL,
    cor_frase VARCHAR(30) NOT NULL,
    frase VARCHAR(200),
    altura DOUBLE NOT NULL,
    largura DOUBLE NOT NULL,
    valor_placa DECIMAL(10 , 2 ) NOT NULL,
    valor_entrada DECIMAL(10 , 2 ) NOT NULL,
    valor_total DECIMAL(10 , 2 ) NOT NULL,
    data_entrega VARCHAR(15),
    status_encomenda VARCHAR(30) NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente)
        REFERENCES clientes (id)
);

SELECT 
    P.id,
    C.nome,
    C.telefone,
    P.data_encomenda,
    P.data_entrega,
    P.valor_placa,
    P.valor_entrada,
    P.status_encomenda
FROM placas AS P
INNER JOIN clientes AS C 
ON (P.id_cliente = C.id);