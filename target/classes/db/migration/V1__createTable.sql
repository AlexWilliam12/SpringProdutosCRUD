CREATE TABLE Produto(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(21) NOT NULL,
	descricao TEXT,
	preco DECIMAL(10, 2) NOT NULL
);