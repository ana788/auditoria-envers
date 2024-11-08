--liquibase formatted sql

--changeset Ana Paula Freire:01
--comment Criação das tabelas de Customer e Address
CREATE TABLE customer(
  id serial NOT NULL,
  email_address VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE address(
  id serial NOT NULL,
  city VARCHAR(255),
  country VARCHAR(255),
  customer_id int4,
  state_code VARCHAR(255),
  street_address VARCHAR(255),
  zip_code VARCHAR(255),
  CONSTRAINT address_pk PRIMARY KEY (id),
  CONSTRAINT address_fk FOREIGN KEY (customer_id) references customer(id)
);

--changeset Ana Paula Freire:02
--comment Criação das tabelas de Auditoria Customer, Address e Revision
CREATE TABLE revision_info (
    id serial NOT NULL,
    rev_timestamp bigint NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    CONSTRAINT revision_info_pk PRIMARY KEY (id)
);

CREATE TABLE customer_audit (
  revision_id INTEGER NOT NULL,
  id serial NOT NULL,
  revision_type int4 NOT NULL,
  email_address VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  PRIMARY KEY (revision_id, id),
  CONSTRAINT idfk_customer_revinfo_rev_id
	FOREIGN KEY (revision_id) REFERENCES revision_info (id)
);

CREATE TABLE address_audit (
  revision_id INTEGER NOT NULL,
  id serial NOT NULL,
  revision_type int4 NOT NULL,
  city VARCHAR(255),
  country VARCHAR(255),
  customer_id Integer,
  state_code VARCHAR(255),
  street_address VARCHAR(255),
  zip_code VARCHAR(255),
  PRIMARY KEY (revision_id, id),
  CONSTRAINT idfk_address_revinfo_rev_id
	FOREIGN KEY (revision_id) REFERENCES revision_info (id)
);

--changeset Ana Paula Freire:03
--comment Sequence
CREATE SEQUENCE hibernate_sequence START 1;

--changeset Ana Paula Freire:04
--comment Criação das tabelas usuário, role e intermdiária
create table user_info (
        id serial NOT NULL,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	email_address varchar(50) not null,
	password varchar(500) not null,
        CONSTRAINT user_info_pk PRIMARY KEY (id)
);

create unique index idx_user_email on user_info (email_address);

create table roles (
  id serial NOT NULL,
  role_name varchar(50) not null,
  CONSTRAINT roles_pk PRIMARY KEY (id)
);

create unique index ids_roles_rolename on roles (role_name);

create table user_roles (
	id serial NOT NULL,
	user_id int not null,
	role_id int not null,
        CONSTRAINT user_roles_pk PRIMARY KEY (id),
	constraint fk_userroles_user_id foreign key(user_id) references user_info(id),
	constraint fk_userroles_role_id foreign key(role_id) references roles(id)
);

create unique index idx_user_role_id on user_roles (user_id, role_id);

--changeset Ana Paula Freire:05
--comment Inserção de usário e roles
-- Password is admin01@123#
INSERT INTO user_info (first_name, last_name, email_address, password)
VALUES ('Super', 'Admin 01', 'admin01@tw.com', '$2a$10$4LEwPTJ86OF/oZUn8hl0vOhSUhFqX5YwNO./i/bTeTD6cn5lRLj2S');

-- Password is admin02@123#
INSERT INTO user_info (first_name, last_name, email_address, password)
VALUES ('Super', 'Admin 02', 'admin02@tw.com', '$2a$10$TgtSzZ9bOt5voyb5bYkdGupQdFMSC9.mhFU1hwziOSIGzcoPN4D9e');


-- Password is welcome@123#
INSERT INTO user_info (first_name, last_name, email_address, password)
VALUES ('Test', 'User', 'user01@tw.com', '$2a$10$yPIWEiYj8sGLox.9cPKPZe6GgGRy.T8iV/sR2Br1PyA0UzLaYVOa.');



INSERT INTO roles (id, role_name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO roles (id, role_name)
VALUES (2, 'ROLE_USER');

INSERT INTO user_roles (user_id, role_id)
select u.id, r.id
from user_info u, roles r
where u.email_address = 'user01@tw.com'
and r.role_name = 'ROLE_USER';

INSERT INTO user_roles (user_id, role_id)
select u.id, r.id
from user_info u, roles r
where u.email_address = 'admin01@tw.com'
and r.role_name = 'ROLE_ADMIN';

INSERT INTO user_roles (user_id, role_id)
select u.id, r.id
from user_info u, roles r
where u.email_address = 'admin02@tw.com'
and r.role_name = 'ROLE_ADMIN';



