INSERT INTO Data.report.City (name)
VALUES ('Curitiba'),
       ('Amazonas');

INSERT INTO Data.report.Contributor (name)
VALUES ('Lois');

INSERT INTO Data.report.Job (name, contributor_fk)
VALUES ('Manage Engineering', 1);

INSERT INTO Data.report.Address (number, contributor_fk, city_fk)
VALUES (1, 1, 1),
       (4, 1, 2);

INSERT INTO Data.report.Contributor (name)
VALUES ('Niko');

INSERT INTO Data.report.Job (name, contributor_fk)
VALUES ('Software Engineering', 2);

INSERT INTO Data.report.Address (number, contributor_fk, city_fk)
VALUES (2, 2, 2),
       (3, 2, 1);

