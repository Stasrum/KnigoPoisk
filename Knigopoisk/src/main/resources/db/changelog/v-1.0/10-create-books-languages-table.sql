CREATE TABLE books_languages (
    book_id BIGINT NOT NULL,
    lang_id BIGINT NOT NULL)

GO

ALTER TABLE IF EXISTS books_languages ADD CONSTRAINT FKl68g8s26vflqai8i75at86svi
    FOREIGN KEY (book_id) REFERENCES books

GO

ALTER TABLE IF EXISTS books_languages ADD CONSTRAINT FKoi45n8u7b7qqh09wwtb15ymps
    FOREIGN KEY (lang_id) REFERENCES languages

GO

INSERT INTO books_languages (book_id, lang_id) VALUES (1,1), (2,1),(3,1), (4,2)

GO
