CREATE database if not exists offer;
USE offer;

CREATE TABLE IF NOT EXISTS continent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS country (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    continent_id INT,
    CONSTRAINT FK_continent_id FOREIGN KEY (continent_id) REFERENCES continent(id)
);

