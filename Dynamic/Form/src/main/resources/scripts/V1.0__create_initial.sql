create table Data.dbo.FormTemplate
(
    id                 bigint       not null identity (1,1),
    name               varchar(100) not null,
    version            int          not null,
    system             varchar(50)  not null,
    constraint PK_FormTemplate primary key (id)
);

create table Data.dbo.FieldTemplate
(
    id                  bigint        not null identity (1,1),
    formTemplate_fk     bigint        not null,
    name                varchar(100)  not null,
    position            integer       not null,
    type                varchar(50)   not null,
    isRequired          bit           not null,
    output              varchar(500),
    constraint PK_Field primary key (id),
    constraint FK_Field_FormTemplate foreign key (formTemplate_fk) references Data.dbo.FormTemplate(id)
);

create table Data.dbo.FormData
(
    id                         bigint       not null identity (1,1),
    formTemplate_fk            bigint   not null,
    constraint PK_FormData primary key (id),
    constraint FK_FormData_FormTemplate foreign key (formTemplate_fk) references Data.dbo.FormTemplate(id)
);

create table Data.dbo.FieldData
(
    id                          bigint       not null identity (1,1),
    value                       varchar(500)   not null,
    field_fk                    bigint   not null,
    formData_fk                 bigint   not null,
    constraint PK_FieldData      primary key (id),
    constraint FK_FieldData_FormData foreign key (formData_fk) references Data.dbo.FormData(id)
);
