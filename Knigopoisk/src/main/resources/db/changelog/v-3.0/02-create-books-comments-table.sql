CREATE TABLE books_comments
(
    book_id BIGINT NOT NULL,
    comment_id BIGINT NOT NULL
)

GO

ALTER TABLE IF EXISTS books_comments ADD CONSTRAINT FKbk7g8s26vflqai8i75at86svi
    FOREIGN KEY (book_id) REFERENCES books

GO

ALTER TABLE IF EXISTS books_comments ADD CONSTRAINT FKbk75n8u7b7qqh09wwtb15ymps
    FOREIGN KEY (comment_id) REFERENCES comments

GO

INSERT INTO books_comments(book_id,comment_id) VALUES (1,1), (1,2),(2,3), (1,4)

GO
