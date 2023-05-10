INSERT INTO users (id, name, surname, city, country, email, phone, locked, password, role, bucket_id)
VALUES  (1, 'admin', 'Ivanov', 'Samara', 'Russia', 'mail@mail.ru', '+79999999999', false, 'pass', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2;