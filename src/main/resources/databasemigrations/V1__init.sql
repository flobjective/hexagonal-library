CREATE TABLE book (
  id uuid not null primary key,
  inventory_number varchar(36) not null unique,
  title varchar not null,
  isbn10 varchar(10) not null,
  isbn13 varchar(13) not null
);