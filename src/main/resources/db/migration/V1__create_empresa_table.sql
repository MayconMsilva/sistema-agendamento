CREATE TABLE empresa (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    horario_abertura TIME NOT NULL,
    horario_fechamento TIME NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);