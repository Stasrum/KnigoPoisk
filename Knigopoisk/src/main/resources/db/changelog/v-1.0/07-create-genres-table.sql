CREATE TABLE genres (
    id SERIAL,
    name VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)
)

GO
INSERT INTO genres (name) VALUES ('Детектив'), ('Фантастика'), ('Приключения')
GO