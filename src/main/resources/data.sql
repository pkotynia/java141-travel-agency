TRUNCATE TABLE country;
TRUNCATE TABLE continent;
INSERT INTO continent (name)
   values ('Europe'),
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