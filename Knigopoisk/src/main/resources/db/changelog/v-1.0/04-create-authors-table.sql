CREATE TABLE authors (
    id SERIAL,
    name VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)
)

GO
INSERT INTO authors (name)
VALUES ('Ник Перумов'), ('Астрид Лингрен'), ('Лев Толстой')
GO