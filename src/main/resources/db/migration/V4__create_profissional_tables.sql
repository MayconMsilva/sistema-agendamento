CREATE TABLE profissional (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE REFERENCES usuario(id),
    empresa_id BIGINT NOT NULL REFERENCES empresa(id),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE profissional_servico (
    profissional_id BIGINT NOT NULL REFERENCES profissional(id),
    servico_id BIGINT NOT NULL REFERENCES servico(id),
    PRIMARY KEY (profissional_id, servico_id)
);