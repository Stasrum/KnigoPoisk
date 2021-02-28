CREATE TABLE book_images (
    id bigint,
    book_id bigint not null,
    path varchar (255) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO

alter table if exists book_images
    add constraint FK1idasmv4m08y4m3usdilrdt0d
    foreign key (book_id)
    references books

GO

INSERT INTO book_images VALUES (1, 1, '0af1f055-b221-4927-911c-1b0631f8dfa3tv1.jpg'),
    (2, 2, '5dbd7dab-3f8f-4be3-86f5-a3b3ec9b534btv4.jpg'),
    (3, 3, '6fe242d7-e633-4405-bdef-eea6da53f6detv6.jpg'),
    (4, 4, '7b284ca2-ab86-49d7-85a7-4b415b15074ftv1.jpg')

GO