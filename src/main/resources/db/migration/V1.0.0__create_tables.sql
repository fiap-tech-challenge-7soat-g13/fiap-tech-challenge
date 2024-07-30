CREATE TABLE "customers" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    document VARCHAR NOT NULL
);

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
    status VARCHAR NOT NULL,
    payment_status VARCHAR,
    total NUMERIC NOT NULL,
    customer_id BIGINT REFERENCES customers (id)
);

CREATE TABLE "orders_products" (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    product_id BIGINT NOT NULL REFERENCES products (id),
    quantity INT NOT NULL,
    price NUMERIC NOT NULL
);
