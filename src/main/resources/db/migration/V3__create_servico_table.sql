CREATE TABLE servico (
    id BIGSERIAL PRIMARY KEY,
    empresa_id BIGINT NOT NULL REFERENCES empresa(id),
    nome VARCHAR(150) NOT NULL,
    duracao_minutos INTEGER NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);