CREATE TABLE books (
    id SERIAL,
    title VARCHAR(255) NOT NULL,
    author_id INT  NOT NULL,
    year INT,
    isbn VARCHAR(13),
    lang_id INT,
    genre_id INT,
    publisher_id INT,


    description TEXT NULL,
    PRIMARY KEY (id))
GO
INSERT INTO
    books (title, author_id, year, isbn, lang_id, genre_id, publisher_id, description)
    VALUES ('Череп на рукаве',1,2002,'1234567890123',1,2,1,'Описание книги'),
           ('Вторая',2,2002,'1234547890123',2,2,2,'просто интфа'),
           ('Третья',3,2001,'1234527890123',1,3,1,'просто интфа')
GO


