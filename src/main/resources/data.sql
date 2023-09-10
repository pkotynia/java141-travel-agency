DELETE FROM country;
ALTER TABLE country AUTO_INCREMENT = 1;

DELETE FROM continent;
ALTER TABLE continent AUTO_INCREMENT = 1;

INSERT INTO continent (name)
VALUES
    ('Europe'),
    ('North America'),
    ('South America'),
    ('Asia'),
    ('Africa'),
    ('Australia');

INSERT INTO country (name, continent_id)
VALUES
    ('Argentina', 1),
    ('Bolivia', 1),
    ('Brazil', 1),
    ('Chile', 1),
    ('Colombia', 1),
    ('Ecuador', 1),
    ('Guyana', 1),
    ('Paraguay', 1),
    ('Peru', 1),
    ('Suriname', 1),
    ('Uruguay', 1),
    ('Venezuela', 1);

INSERT INTO offer (country_id)
VALUES (1);