DROP TABLE hibernate_sequence_table;
DROP TABLE supplier;
DROP TABLE customer;

CREATE TABLE  hibernate_sequence_table (
  id bigserial NOT NULL,
  sequence_name varchar(255) NOT NULL,
  next_val bigint NOT NULL,
  PRIMARY KEY  (id)
);

CREATE TABLE supplier
(
    id bigint PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE ,
    linkman VARCHAR(64),
    tel VARCHAR(32),
    fax VARCHAR(32),
    email VARCHAR(256),
    address VARCHAR(256)
);

CREATE TABLE customer
(
  id bigint PRIMARY KEY ,
  name VARCHAR(128) NOT NULL UNIQUE ,
  linkman VARCHAR (64),
  tel VARCHAR (32),
  fax VARCHAR (32),
  email VARCHAR (256),
  address VARCHAR (256)
);
