CREATE TABLE IF NOT EXISTS public.category
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    category_default_id bigserial,
    PRIMARY KEY (id)
);