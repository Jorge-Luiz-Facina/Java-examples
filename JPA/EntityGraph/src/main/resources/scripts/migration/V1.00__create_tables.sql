CREATE TABLE Data.report.Contributor
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    name varchar(255),
    CONSTRAINT PK_Contributor PRIMARY KEY (id)
);

CREATE TABLE Data.report.Job
(
    id             bigint IDENTITY (1, 1) NOT NULL,
    name           varchar(255),
    contributor_fk bigint                 NOT NULL,
    CONSTRAINT PK_Job PRIMARY KEY (id),
    CONSTRAINT FK_JobContributor FOREIGN KEY (contributor_fk) REFERENCES Data.report.Contributor (id)
);

CREATE TABLE Data.report.City
(
    id   bigint IDENTITY (1, 1) NOT NULL,
    name varchar(255),
    CONSTRAINT PK_City PRIMARY KEY (id)
);

CREATE TABLE Data.report.Address
(
    id             bigint IDENTITY (1, 1) NOT NULL,
    number         int,
    contributor_fk bigint                 NOT NULL,
    city_fk        bigint                 NOT NULL,
    CONSTRAINT PK_Address PRIMARY KEY (id),
    CONSTRAINT FK_AddressContributor FOREIGN KEY (contributor_fk) REFERENCES Data.report.Contributor (id),
    CONSTRAINT FK_AddressCity FOREIGN KEY (city_fk) REFERENCES Data.report.City (id)
);