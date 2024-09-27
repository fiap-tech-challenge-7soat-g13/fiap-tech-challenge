CREATE TABLE "payments" (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    payment_at TIMESTAMP NOT NULL,
    total NUMERIC NOT NULL,
    qr_data VARCHAR NOT NULL,
    status VARCHAR NOT NULL
);

ALTER TABLE IF EXISTS public.orders DROP COLUMN IF EXISTS payment_status;