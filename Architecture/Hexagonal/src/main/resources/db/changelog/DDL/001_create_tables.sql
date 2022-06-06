--liquibase formatted sql
--changeset jorge:create-tables

CREATE TABLE IF NOT EXISTS public.book (
   id SERIAL PRIMARY KEY,
   name TEXT NOT NULL,
   releaseDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS public.tag (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.book_tag (
    book_id SERIAL NOT NULL,
    tag_id SERIAL NOT NULL
);

ALTER TABLE
    public.book_tag
ADD
    CONSTRAINT book_tag_book_fk FOREIGN KEY (book_id) REFERENCES public.book(id);

ALTER TABLE
    public.book_tag
ADD
    CONSTRAINT book_tag_tag_fk FOREIGN KEY (tag_id) REFERENCES public.tag(id);
