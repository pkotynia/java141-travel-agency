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
    ('Wroclaw', 1),
    ('Warsaw', 1);

INSERT INTO hotel (name, address, city_id, rating)
VALUES
    ('Hilton', 'Dąbska 5', 1, 8.0),
    ('Qubus', 'Świętej Marii Magdaleny 2', 2, 7.0),
    ('Marriot', 'al. Jerozolimskie 65', 3, 9.0);

INSERT INTO airport (name, address, city_id)
VALUES
    ('Międzynarodowy Port Lotniczy im. Jana Pawła II Kraków-Balice', 'Kapitana Mieczysława Medweckiego 1', 1),
    ('Port Lotniczy Wrocław S.A.', 'Graniczna 190', 2),
    ('Lotnisko Chopina', 'Żwirki i Wigury 1', 3),
    ('Lotnisko Warszawa Modlin', 'Generała Wiktora Thommée 1a', 3);

INSERT INTO offer (name, hotel_id, price)
VALUES
    ('Cracow_Summer_offer',1,100.0),
    ('Wroclaw_Autumn_offer',2,200.0),
    ('Warsaw_Summer_offer',3,300.0);
