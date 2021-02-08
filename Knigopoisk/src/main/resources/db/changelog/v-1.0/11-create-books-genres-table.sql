CREATE TABLE books_genres (book_id BIGINT NOT NULL,genre_id BIGINT NOT NULL)

GO

ALTER TABLE IF EXISTS books_genres ADD CONSTRAINT FKlv42b6uemg63q27om39jjbt9o
    FOREIGN KEY (book_id) REFERENCES books(id)

GO

ALTER TABLE IF EXISTS books_genres ADD CONSTRAINT FKgkat05y2cec3tcpl6ur250sd0
    FOREIGN KEY (genre_id) REFERENCES genres(id)

GO

INSERT INTO books_genres (book_id, genre_id)
VALUES (1,2),
       (2,2),
       (3,2),
       (4,2)

GO



