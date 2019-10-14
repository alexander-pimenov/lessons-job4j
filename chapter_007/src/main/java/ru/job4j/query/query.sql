--product(id, name, type_id, expired_date, price)
--type(id, name)

CREATE TABLE type (
  ID serial primary key,
  name varchar (50)
);

INSERT INTO type(name) values('СЫР'), ('МОЛОКО'), ('мороженное');

CREATE TABLE product (
  ID serial primary key,
  name varchar (50),
  expired_date timestamp,
  price integer,
  type_id INTEGER REFERENCES type(ID)
);

INSERT INTO product(name, expired_date, price, type_id) values('Вологда', '1999-01-08 04:05:06', 100, 1),
('Колхоз', '1999-01-08 04:05:06', 90, 1),
('Лось', '1999-01-08 04:05:06', 40, 1),
('new', '1999-01-08 04:05:06', 54, 2),
('fote', '1999-01-08 04:05:06', 60, 2),
('gore', '1999-01-08 04:05:06', 44, 2),
('doer', '1999-01-08 04:05:06', 40, 3),
('Вологда мороженное', '1999-01-08 04:05:06', 130, 3),
('wiw', '2019-04-17 04:05:06', 140, 3);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT product.name FROM product LEFT JOIN type ON product.type_id = type.id
Where type.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT name FROM product
Where name like '%мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT name FROM product
where extract (MONTH FROM expired_date) - extract (MONTH FROM now()) = 1;

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT name FROM product
WHERE price = (
SELECT MAX(price) FROM product
);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(product.type_id) FROM product LEFT JOIN type ON product.type_id = type.id
Where type.name = 'СЫР';

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT product.name FROM product LEFT JOIN type ON product.type_id = type.id
Where type.name = 'СЫР' or type.name = 'МОЛОКО';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT type.name FROM product LEFT JOIN type ON product.type_id = type.id
group by type.name having count(product.id) < 10;

--8. Вывести все продукты и их тип.
SELECT type.name as Type_name, product.name as Product_name FROM product LEFT JOIN type ON product.type_id = type.id

