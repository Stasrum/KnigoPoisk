CREATE TABLE publishers (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    description VARCHAR(255),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO
INSERT INTO publishers (name) VALUES ('Эксмо'), ('Williams'), ('R$D')
GO