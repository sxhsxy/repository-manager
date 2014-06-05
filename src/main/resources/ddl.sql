DROP TABLE supplier;
DROP TABLE hibernate_sequence_table;

CREATE TABLE supplier
(
    id bigint PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE ,
    phone VARCHAR(128),
    address VARCHAR (256)
);



CREATE TABLE  hibernate_sequence_table (
  id bigserial NOT NULL,
  sequence_name varchar(255) NOT NULL,
  next_val bigint NOT NULL,
  PRIMARY KEY  (id)
);

