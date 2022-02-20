--liquibase formatted sql
--changeset jorge:create-tables

create table data.credit.account
(
    id           bigint       not null identity (1,1),
    name         varchar(255) not null,
    description  varchar(255) not null,

    constraint PK_account primary key (id)
);
