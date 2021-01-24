CREATE TABLE publishers (
    id SERIAL,
    name VARCHAR (45) NOT NULL,
    PRIMARY KEY (id)
)

GO
INSERT INTO publishers (name) VALUES ('Эксмо'), ('Williams'), ('R$D')
GO