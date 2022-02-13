--liquibase formatted sql
--changeset jorge:create-tables

CREATE TABLE IF NOT EXISTS public.group (
   id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS public.user (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    age integer  NOT NULL,
    group_id SERIAL NOT NULL,
    UNIQUE (group_id)
);

ALTER TABLE
    public.user
ADD
    CONSTRAINT user_group_fk FOREIGN KEY (group_id) REFERENCES public.group(id);

CREATE TABLE IF NOT EXISTS public.plan (
    id SERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS public.user_plan (
    user_id SERIAL NOT NULL,
    plan_id SERIAL NOT NULL,
    UNIQUE (user_id),
    UNIQUE (plan_id)
);


ALTER TABLE
    public.user_plan
ADD
    CONSTRAINT user_plan_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id);

ALTER TABLE
    public.user_plan
ADD
    CONSTRAINT user_plan_plan_fk FOREIGN KEY (plan_id) REFERENCES public.plan(id);

CREATE TABLE IF NOT EXISTS public.address (
    id SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL,
    UNIQUE (user_id)
);

ALTER TABLE
    public.address
ADD
    CONSTRAINT address_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id);

CREATE TABLE IF NOT EXISTS public.account (
    id SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL,
    UNIQUE (user_id)
);

ALTER TABLE
    public.account
ADD
    CONSTRAINT address_user_fk FOREIGN KEY (user_id) REFERENCES public.user(id);

