CREATE TABLE likes (
    id BIGSERIAL,
    book_id bigint not null,
    user_id bigint not null,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

)

GO

ALTER TABLE IF EXISTS likes
    ADD CONSTRAINT FK_likes4booksm3usdilrdt0d
    FOREIGN KEY (book_id)
    REFERENCES books

GO

ALTER TABLE IF EXISTS likes
    ADD CONSTRAINT FK_likes4usersm3usdilrdt0d
        FOREIGN KEY (user_id)
            REFERENCES users

GO

INSERT INTO likes (book_id, user_id) VALUES (1, 1), (1,2), (1,3),(2,1),(2,3),(3,3)

GO