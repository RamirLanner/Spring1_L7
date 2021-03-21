create table products
(
    id         bigserial primary key,
    title      varchar(255),
    cost       float,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, cost)
values ('Apple', 12.3),
       ('Orange', 16.4),
       ('Sweet Roll', 5.21),
       ('Moon Sugar', 66.3),
       ('Skooma', 77.88),
       ('Mace of Molag Bal', 100.3),
       ('Skeleton Key', 560.35),
       ('Wabbajack', 999.99),
       ('Azura''s Star', 66.6),
       ('Mehrune''s Razor', 95.23),
       ('Sanguine Rose', 54.32),
       ('phone', 25.88),
       ('shoes', 45.88),
       ('clothe', 234.88),
       ('ring', 23235.88),
       ('dress', 435.5),
       ('newspaper', 653.4),
       ('book', 453.88),
       ('tea', 739.25),
       ('sock', 36.2);