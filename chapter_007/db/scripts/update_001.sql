create table if not exists items (
   id serial primary key not null,
   item_name varchar(250),
   description text,
   created timestamp,
   comments text
);