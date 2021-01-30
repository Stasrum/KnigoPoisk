CREATE TABLE authors (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO
INSERT INTO authors (name)
VALUES ('Ник Перумов'), ('Астрид Лингрен'), ('Лев Толстой'), ('Дмитрий Глуховский')
GO