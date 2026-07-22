CREATE TABLE agendamento (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL REFERENCES usuario(id),
    profissional_id BIGINT NOT NULL REFERENCES profissional(id),
    servico_id BIGINT NOT NULL REFERENCES servico(id),
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_fim TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_agendamento_profissional_data
    ON agendamento (profissional_id, data_hora_inicio, data_hora_fim);