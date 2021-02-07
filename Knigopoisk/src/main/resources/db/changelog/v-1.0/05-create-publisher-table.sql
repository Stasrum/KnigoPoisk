CREATE TABLE publishers (
    id BIGSERIAL,
    name VARCHAR (45) UNIQUE NOT NULL,
    description TEXT DEFAULT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)

GO

INSERT INTO publishers (name,description) VALUES ('Эксмо — АСТ',''),
                                     ('Williams',''),
                                     ('ОЛМА Медиа Групп/ИД Просвещение',''),
                                     ('Азбука — Аттикус',''),
                                     ('Экзамен',''),
                                     ('Росмэн',''),
                                     ('Просвещение',''),
                                     ('Бином. Лаборатория знаний','')

GO