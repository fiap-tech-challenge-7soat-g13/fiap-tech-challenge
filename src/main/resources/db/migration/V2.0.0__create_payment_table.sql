CREATE TABLE "payments" (
    id BIGSERIAL PRIMARY KEY,
    uuid UUID NOT NULL,
    order_id BIGINT NOT NULL REFERENCES orders (id),
    qr_code VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    external_id VARCHAR
);

ALTER TABLE IF EXISTS public.orders DROP COLUMN IF EXISTS payment_status;