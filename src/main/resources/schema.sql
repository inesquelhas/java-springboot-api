create table if not exists addresses (
   address_id int primary key,
   postal_code_id varchar(8) not null,
   locale_number varchar(10) not null,
   additional_info varchar(100) not null,
   creation_at timestamp not null,
   updated_at timestamp null
);

create table if not exists clients (
   client_id int primary key,
   --address_id int not null,
   email varchar(100) not null,
   password varchar(100) not null,
   name varchar(100) not null,
   birth_date date not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   active char(1)
   --constraint fk_clients_address foreign key (address_id) references addresses(address_id)
);

create table if not exists identifications (
   identification_id int primary key,
   client_id int not null,
   ident_type integer not null,
   ident_code varchar(100) not null,
   emission_date timestamp not null,
   valid_date timestamp not null,
   creation_at timestamp not null,
   updated_at timestamp null,
   constraint fk_clients_identifications foreign key (client_id) references clients(client_id)
);

create table if not exists roles (
   role_id int primary key,
   description varchar(200) not null
);

create table if not exists clients_roles (
   client_id int not null,
   role_id int not null,
   constraint pk_clients_roles primary key (client_id, role_id),
   constraint fk_clients_roles_client foreign key (client_id) references clients(client_id),
   constraint fk_clients_roles_role foreign key (role_id) references roles(role_id)
);

-- SEQUENCES
create sequence if not exists seq_clients start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_addresses start with 1 increment by 50 maxvalue 99999999;
create sequence if not exists seq_identifications start with 1 increment by 50 maxvalue 99999999;

-- CONSTRAINTS
alter table clients add constraint clients_email_unique unique(email);