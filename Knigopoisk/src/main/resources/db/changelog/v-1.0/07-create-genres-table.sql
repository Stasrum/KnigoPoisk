CREATE TABLE genres (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO
INSERT INTO genres (name) VALUES ('Детектив'), ('Фантастика'), ('Приключения')
GO