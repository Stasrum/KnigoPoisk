CREATE TABLE comments (
    id BIGSERIAL,
    user_id bigint not null ,
    text TEXT NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO

INSERT INTO comments (user_id,text) VALUES (1,'Какая классная книга'),
                                           (1,'Всем рекомендую прочитать 5 баллов'),
                                           (1,'Миры так захватывают'),
                                           (1,'На одном дыхании. Что еще такого почитать?')

GO