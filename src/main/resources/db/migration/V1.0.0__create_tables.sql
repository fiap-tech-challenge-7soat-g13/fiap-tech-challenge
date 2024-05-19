CREATE TABLE "customers" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    document VARCHAR(255)
);

CREATE TABLE "products" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price NUMERIC NOT NULL,
    removed BOOLEAN NOT NULL
);

CREATE TABLE "orders" (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,
    total NUMERIC NOT NULL,
    customer_id BIGINT NOT NULL REFERENCES customers (id)
);

CREATE TABLE "orders_products" (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    product_id BIGINT NOT NULL REFERENCES products (id),
    quantity INT NOT NULL,
    price NUMERIC NOT NULL
);
