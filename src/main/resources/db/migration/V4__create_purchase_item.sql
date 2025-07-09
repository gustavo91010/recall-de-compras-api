CREATE TABLE purchase_item (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT REFERENCES product(id),
    quantity DOUBLE PRECISION DEFAULT 0,
    purchase_id BIGINT NOT NULL REFERENCES purchase(id)
);
