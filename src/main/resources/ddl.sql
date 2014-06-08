DROP TABLE hibernate_sequence_table;
DROP TABLE sys_role_permission;
DROP TABLE sys_user_role;
DROP TABLE sys_user;
DROP TABLE sys_role;
DROP TABLE sys_permission;
DROP TABLE purchase_record_detail;
DROP TABLE sale_record_detail;
DROP TABLE sale_record;
DROP TABLE purchase_record;
DROP TABLE commodity_inventory;
DROP TABLE supplier;
DROP TABLE customer;
DROP TABLE commodity;


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


CREATE TABLE sys_user
(
  id BIGINT PRIMARY KEY NOT NULL,
  login_name VARCHAR(128) NOT NULL,
  password VARCHAR(256) NOT NULL,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE sys_role
(
  id bigint PRIMARY KEY ,
  name VARCHAR(128) UNIQUE
);

CREATE TABLE sys_permission
(
  id bigint NOT NULL,
  name VARCHAR(128) UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE sys_role_permission
(
  role_id bigint NOT NULL,
  permission_id bigint NOT NULL,
  PRIMARY KEY (role_id, permission_id)
);

CREATE TABLE sys_user_role
(
  user_id bigint NOT NULL ,
  role_id bigint NOT NULL ,
  PRIMARY KEY (user_id, role_id)
);

CREATE TABLE commodity
(
  id bigint,
  barcode VARCHAR (16) UNIQUE,
  name VARCHAR (64) NOT NULL ,
  unit VARCHAR (16),
  cost DECIMAL (16,2),
  price DECIMAL (16,2),
  PRIMARY KEY (id)
);

CREATE TABLE purchase_record
(
  id BIGINT,
  supplier_id BIGINT REFERENCES supplier(id),
  datetime TIMESTAMPTZ,
  PRIMARY KEY (id)
);

CREATE TABLE purchase_record_detail
(
  id BIGINT,
  purchase_record_id BIGINT REFERENCES purchase_record(id),
  commodity_id BIGINT REFERENCES commodity(id),
  cost DECIMAL (16,2),
  quantity BIGINT,
  PRIMARY KEY(id)
);

CREATE TABLE sale_record
(
  id BIGINT,
  customer_id BIGINT REFERENCES customer(id),
  datetime TIMESTAMPTZ,
  PRIMARY KEY (id)
);

CREATE TABLE sale_record_detail
(
  id BIGINT,
  sale_record_id BIGINT REFERENCES sale_record(id),
  commodity_id BIGINT REFERENCES commodity(id),
  price DECIMAL (16,2),
  quantity BIGINT ,
  PRIMARY KEY (id)
);

CREATE TABLE commodity_inventory
(
  commodity_id BIGINT REFERENCES commodity(id),
  initial_quantity BIGINT ,
  input_quantity BIGINT,
  output_quantity BIGINT,
  left_quantity BIGINT,
  PRIMARY KEY (commodity_id)
);
