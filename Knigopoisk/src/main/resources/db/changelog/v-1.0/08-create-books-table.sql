CREATE TABLE books (
    id BIGSERIAL,
    title VARCHAR(255) UNIQUE NOT NULL,
    author_id INT NOT NULL,
    year INT,
    isbn VARCHAR(13),
    lang_id BIGINT,
    genre_id BIGINT,
    publisher_id BIGINT,
    description TEXT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id))
GO

ALTER TABLE IF EXISTS books
    ADD CONSTRAINT FKfjixh2vym2cvfj3ufxj91jem7
        FOREIGN KEY (author_id)
            REFERENCES authors
GO

ALTER TABLE IF EXISTS books
    ADD CONSTRAINT FK83whbxkeejtgmc6f9wjacg5s9
        FOREIGN KEY (lang_id)
            REFERENCES languages
GO

ALTER TABLE IF EXISTS books
    ADD CONSTRAINT FK9hsvoalyniowgt8fbufidqj3x
        FOREIGN KEY (genre_id)
            REFERENCES genres
GO

ALTER TABLE IF EXISTS books
    ADD CONSTRAINT FKayy5edfrqnegqj3882nce6qo8
        FOREIGN KEY (publisher_id)
            REFERENCES publishers
GO


INSERT INTO
    books (title, author_id, year, isbn, lang_id, genre_id, publisher_id, description)
    VALUES ('Череп на рукаве',1,2002,'1234567890123',1,2,1,'Описание книги'),
           ('Вторая',2,2002,'1234547890123',2,2,2,'просто интфа'),
           ('Третья',3,2001,'1234527890123',1,3,1,'просто интфа')
GO


