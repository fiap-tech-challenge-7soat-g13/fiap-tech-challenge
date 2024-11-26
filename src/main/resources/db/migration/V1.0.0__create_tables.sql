CREATE TABLE "products" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    category VARCHAR NOT NULL,
    price NUMERIC NOT NULL,
    active BOOLEAN NOT NULL,
    images VARCHAR[] NOT NULL
);

CREATE TABLE "orders" (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    status SMALLINT NOT NULL,
    total NUMERIC NOT NULL,
    customer_id BIGINT NOT NULL,
    payment_id BIGINT
);

CREATE TABLE "orders_products" (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    product_id BIGINT NOT NULL REFERENCES products (id),
    quantity INT NOT NULL,
    price NUMERIC NOT NULL
);
