CREATE TABLE authors (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    description TEXT DEFAULT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO
INSERT INTO authors (name)
VALUES ('Ник Перумов'),
       ('Агата Кристи'),
       ('Блейк Крауч'),
       ('Джо Аберкромби'),
       ('Дмитрий Глуховский'),
       ('Астрид Лингрен'),
       ('Лев Толстой')

GO