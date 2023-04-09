CREATE TABLE Data.register.Store
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    name       varchar(255),

    CONSTRAINT PK_Store PRIMARY KEY (id)
);