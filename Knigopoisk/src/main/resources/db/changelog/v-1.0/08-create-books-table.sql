CREATE TABLE books (
    id BIGSERIAL,
    title VARCHAR(255) NOT NULL,
    year INT,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    publisher_id BIGINT NOT NULL,
    description TEXT DEFAULT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id))
GO

ALTER TABLE IF EXISTS books ADD CONSTRAINT FKayy5edfrqnegqj3882nce6qo8
        FOREIGN KEY (publisher_id) REFERENCES publishers
GO


INSERT INTO
    books (title, year, isbn, publisher_id, description)
    VALUES ('Череп на рукаве',2002,'9785699110506',1,'Этот роман – фантастический боевик, написанный в лучших традициях жанра. Герой книги – Руслан Фатеев, уроженец планеты Новый Крым, – идеальный солдат грядущей войны с Чужими, жуткими и загадочными монстрами, остановить которых не в силах никто и ничто. Но это будет потом, а пока форма имперского десантника, до боли напоминающая форму солдат вермахта времен Второй мировой, ложится на его плечи как клеймо предателя, покинувшего свой дом и вступившего в ряды оккупантов. ISBN 978-5-699-11050-6'),
           ('Убийство в Восточном экспрессе',2016,'9785699886326',1,'Роман «Убийство в “Восточном экспрессе”» по своему значению для мирового остросюжетного жанра стоит рядом с «Десятью негритятами»; на нем выросло не одно поколение знаменитых ныне авторов. Это образец того, как надо писать «детектив замкнутого пространства». Значение этого культового романа лишь увеличилось в силу нескольких блестящих экранизаций, ставших мировой киноклассикой.'),
           ('Темная материя',2016,'9785699936441',1,'Джейсон Дессен, выдающийся физик, некогда отказался от блестящей научной карьеры и стал обычным преподавателем в колледже. Теперь все его внимание отдано семье – любимым жене и сыну. Они для Джейсона важнее всего. И вдруг – это нелепое похищение… Неизвестный в маске напал на Дессена на улице, под дулом револьвера усадил его в машину, отвез к заброшенному зданию и ввел ему в вену непонятный препарат. Джейсон потерял сознание. А очнувшись, обнаружил себя окруженным массой людей; все они обращались к нему как к старому другу и наперебой поздравляли его с возвращением – и с тем, что его открытие наконец-то сработало. Вот только Дессен не знал никого из этих людей. И уж тем более не ведал, что за открытие совершил…'),
           ('Кровь и железо',2008,'9785699940356',1,'Союз, одно из сильнейших государств Земного Круга, переживает нелегкие времена. Король при смерти, и от его имени правит Закрытый Совет, члены которого больше озабочены интригами друг против друга, нежели делами страны. Северной провинции угрожает самозваный король Бетод, а на юге готовит месть за предыдущее поражение Гуркхульская империя. Что этому может противопоставить хромой калека-инквизитор Занд дан Глокта, который и ест-то с трудом? Какую роль в надвигающихся событиях сыграют Логен Девятипалый, чье имя приводит в ужас северян, и молодой дворянин Джезаль дан Луфар, которого не заботит никто, кроме него самого?')

GO
