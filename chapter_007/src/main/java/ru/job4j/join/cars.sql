--body. engine, transmission.

CREATE TABLE body (
  ID serial primary key,
  description varchar (100)
);

INSERT INTO body(description) values('Hatchback'), ('Sedan'), ('Wagon'), ('Jeep'), ('Coupe');

CREATE TABLE engine (
  ID serial primary key,
  description varchar (100)
);

INSERT INTO engine(description) values('Diesel'), ('Petrol'), ('Hybrid'), ('Electric');

CREATE TABLE transmission (
  ID serial primary key,
  description varchar (100)
);

INSERT INTO transmission(description) values
('Automatic transmission'),
('Manual transmission'),
('Continuously variable transmission'),
('Semi-automatic and dual-clutch transmissions');

CREATE TABLE car (
  ID serial primary key,
  name varchar (100),
  transmission_ID integer references transmission(ID),
  engine_ID integer references engine(ID),
  body_ID integer references body(ID)
);

INSERT INTO car(name, transmission_ID, engine_ID, body_ID) values
('BMW', 4, 2, 2),
('Lamborghini', 2, 3, 2),
('Porsche', 1, 1, 1),
('Audi', 4, 2, 5);

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT
car.name as car_name,
body.description as body_d,
transmission.description as transmission_d,
engine.description as engine_d
FROM CAR
left join transmission ON car.transmission_ID = transmission.ID
left join engine ON car.engine_ID = engine.ID
left join body ON car.body_ID = body.ID

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT transmission.description as transmission_d
FROM CAR right join transmission ON car.transmission_ID = transmission.ID
WHERE car.id is NULL

SELECT body.description as body_d
FROM CAR right join body ON car.body_ID = body.ID
WHERE car.id is NULL

SELECT engine.description as engine_d
FROM CAR right join engine ON car.engine_ID = engine.ID
WHERE car.id is NULL