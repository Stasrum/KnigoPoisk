CREATE TABLE genres (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    description TEXT DEFAULT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO
INSERT INTO genres (name, description) VALUES ('Детектив',''),
                                 ('Фантастика',''),
                                 ('Приключения','')
GO