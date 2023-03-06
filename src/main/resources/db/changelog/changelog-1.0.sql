--liquibase formatted sql
--changeset ermakovich:1


CREATE TABLE IF NOT EXISTS finance.accounts
(
    id bigserial PRIMARY KEY,
    account_number character varying(13) NOT NULL,
    balance numeric(38,2) NOT NULL,
    external_id uuid NOT NULL
)
