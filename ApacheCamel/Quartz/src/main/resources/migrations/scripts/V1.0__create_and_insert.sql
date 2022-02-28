create table model.dbo.contract
(
    id          bigint        not null identity (1,1),
    name    varchar(50)   not null,
    expiration date not null,
    automaticRenovation bit not null,
    constraint PK_contract primary key (id),
);

insert into model.dbo.contract values('Test1', '2018-02-01', 1);
insert into model.dbo.contract values('Test2', '2018-05-02', 1);
insert into model.dbo.contract values('Test3', '2099-05-02', 1);
insert into model.dbo.contract values('Test4', '2011-05-02', 0);
