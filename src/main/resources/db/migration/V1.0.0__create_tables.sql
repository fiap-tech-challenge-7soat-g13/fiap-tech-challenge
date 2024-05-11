CREATE TABLE "clients" (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255),
	age INT,
	mail VARCHAR(255),
	document VARCHAR(255)
);

CREATE TABLE "products" (
    id BIGSERIAL PRIMARY KEY,
	description VARCHAR(255),
	brand VARCHAR(255),
	category VARCHAR(255),
	value NUMERIC
);

CREATE TABLE "orders" (
    id BIGSERIAL PRIMARY KEY,
	date TIMESTAMP,
	status VARCHAR(255),
	client_id BIGINT NOT NULL REFERENCES clients (id)
);

CREATE TABLE "orders_products" (
	order_id BIGINT NOT NULL REFERENCES orders (id),
	product_id BIGINT NOT NULL REFERENCES products (id),
	PRIMARY KEY (order_id, product_id)
);
