DELETE FROM offer;
ALTER TABLE offer AUTO_INCREMENT = 1;

DELETE FROM hotel;
ALTER TABLE hotel AUTO_INCREMENT = 1;

DELETE FROM airport;
ALTER TABLE airport AUTO_INCREMENT = 1;

DELETE FROM city;
ALTER TABLE city AUTO_INCREMENT = 1;

DELETE FROM country;
ALTER TABLE country AUTO_INCREMENT = 1;

DELETE FROM continent;
ALTER TABLE continent AUTO_INCREMENT = 1;

DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT = 1;

DELETE FROM authorities;
ALTER TABLE authorities AUTO_INCREMENT = 1;

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
    ('Poland', 1),
    ('Bolivia', 2),
    ('Brazil', 3),
    ('Chile', 4),
    ('Colombia', 1),
    ('Ecuador', 1),
    ('Guyana', 1),
    ('Paraguay', 1),
    ('Peru', 1),
    ('Suriname', 1),
    ('Uruguay', 1),
    ('Venezuela', 1);

INSERT INTO city (name, country_id)
VALUES
    ('Cracow', 1),
    ('Wroclaw', 2),
    ('Warsaw', 3);


INSERT INTO hotel (name, address, city_id, rating)
VALUES
    ('Hilton', 'test', 1, 8.0),
    ('Cubus', 'test', 2, 7.0),
    ('Marriot', 'test', 3, 9.0);

INSERT INTO airport (name, address, city_id)
VALUES
    ('Cracow Airport', 'test', 1),
    ('Wroclaw Airport', 'test', 2),
    ('Warsaw Airport', 'test', 3);

INSERT INTO offer (name, hotel_id, price)
VALUES
    ('Cracow_offer',1,100.0),
    ('Wroclaw_offer',2,100.0),
    ('Warsaw_offer',3,100.0);