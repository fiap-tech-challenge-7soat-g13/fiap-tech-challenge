CREATE TABLE "clients" (
	id bigserial NOT NULL,
	name VARCHAR(255),
	age INT,
	mail VARCHAR(255),
	document VARCHAR(255),
	CONSTRAINT client_pk PRIMARY KEY (id)
);

CREATE TABLE "products" (
	id bigserial NOT NULL,
	description VARCHAR(255),
	brand VARCHAR(255),
	category VARCHAR(255),
	value VARCHAR(255),
	CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE "orders" (
	id bigserial NOT NULL,
	date VARCHAR(255),
	status VARCHAR(255),
	product_id bigserial,
	client_id bigserial,
	CONSTRAINT order_pk PRIMARY KEY (id)
);

ALTER TABLE "orders" ADD CONSTRAINT order_product_fk FOREIGN KEY (product_id) REFERENCES products(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE "orders" ADD CONSTRAINT order_client_fk FOREIGN KEY (client_id) REFERENCES clients(id) ON UPDATE CASCADE ON DELETE CASCADE;

CREATE SEQUENCE client_unique_id_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999999
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE product_unique_id_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999999
	START 1
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE order_unique_id_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999999999
	START 1
	CACHE 1
	NO CYCLE;