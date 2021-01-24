CREATE TABLE roles (
    id SERIAL,
    name VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)
)

GO
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER')
GO