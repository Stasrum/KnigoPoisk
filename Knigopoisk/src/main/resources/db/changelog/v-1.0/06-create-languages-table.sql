CREATE TABLE languages (
    id SERIAL,
    name VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)
)

GO
INSERT INTO languages (name) VALUES ('Русский'), ('Английский'), ('Немецкий')
GO