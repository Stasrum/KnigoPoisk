CREATE TABLE books_authors (book_id BIGINT NOT NULL, author_id BIGINT NOT NULL)

GO

ALTER TABLE IF EXISTS books_authors ADD CONSTRAINT FK1b933slgixbjdslgwu888m34v
    FOREIGN KEY (book_id) REFERENCES books(id)

GO

ALTER TABLE IF EXISTS books_authors ADD CONSTRAINT FK3qua08pjd1ca1fe2x5cgohuu5
    FOREIGN KEY (author_id) REFERENCES authors(id)

GO

INSERT INTO books_authors (book_id, author_id)
    VALUES (1,1),
           (2,2),
           (3,3),
           (4,4)

GO
