CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    measuret_unit VARCHAR(50) NOT NULL,
    price NUMERIC(19,2) DEFAULT 0,
    observation TEXT,
    user_id BIGINT NOT NULL REFERENCES users(id)
);

