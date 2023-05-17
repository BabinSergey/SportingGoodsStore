INSERT INTO products (id, title, price)
values (1, 'Майка', 450.0),
       (2, 'Шорты', 45.0),
       (3, 'Бутсы', 65.0),
       (4, 'Мяч', 115.0),
       (5, 'Гантели', 58.0);

-- INSERT INTO products (id, title, price)
-- values (2, 'Шорты', 45.0);
--
-- INSERT INTO products (id, title, price)
-- values (3, 'Бутсы', 65.0);
--
-- INSERT INTO products (id, title, price)
-- values (4, 'Мяч', 115.0);
--
-- INSERT INTO products (id, title, price)
-- values (5, 'Гантели', 58.0);

ALTER SEQUENCE product_seq RESTART WITH 6;