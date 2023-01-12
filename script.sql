create table person
(
    id    int GENERATED BY DEFAULT AS IDENTITY,
    name  varchar,
    age   int,
    email varchar
);

truncate person restart identity cascade;
insert into person(name, age, email)
values ('Tom', 25, 'tom@gmail.com'),
       ('Bob', 31, 'bob1@gmail.com'),
       ('Bob', 53, 'bob2@gmail.com'),
       ('Bob', 20, 'bob3@gmail.com'),
       ('Katy', 14, 'katy@gmail.com');

alter table person add column created_at timestamp default now(),
    add column updated_at timestamp,
    add column created_who varchar;
