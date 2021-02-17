CREATE TABLE comments (
    id BIGSERIAL,
    user_id bigint not null ,
    book_id bigint not null,
    text TEXT NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)
GO

alter table if exists comments add constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id) references users

GO

alter table if exists comments add constraint FK8omq0tc18jd43bu5tjh6jvrBq
        foreign key (book_id) references books

GO

INSERT INTO comments (user_id,book_id,text) VALUES (1,1,'Какая классная книга'),
                                           (1,1,'Всем рекомендую прочитать 5 баллов'),
                                           (2,2,'Миры так захватывают'),
                                           (2,3,'На одном дыхании. Что еще такого почитать?')

GO