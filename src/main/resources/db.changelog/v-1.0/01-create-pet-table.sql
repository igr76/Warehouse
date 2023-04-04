-- Создание таблицы носков
CREATE TABLE socks
(
    id         integer generated always as identity primary key,
    color      TEXT,
    cotton_part int,
    quantity   int
);

