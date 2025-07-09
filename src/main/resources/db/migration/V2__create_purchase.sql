CREATE TABLE purchase (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT now(),
    update_at TIMESTAMP NOT NULL DEFAULT now(),
    access_token VARCHAR(255) NOT NULL REFERENCES users(access_token));

