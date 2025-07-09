CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    measuret_unit VARCHAR(50) NOT NULL,
    price NUMERIC(19,2) DEFAULT 0,
    observation TEXT,
    access_token VARCHAR(255) NOT NULL REFERENCES users(access_token));

