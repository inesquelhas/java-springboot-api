insert into roles (role_id, description) values (1, 'ROLE_ADMIN');
insert into roles (role_id, description) values (2, 'ROLE_USER');
insert into roles (role_id, description) values (3, 'ROLE_CLIENT');
insert into roles (role_id, description) values (4, 'ROLE_PARTNER');

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


insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active)
values (seq_clients.nextval,'ines@gmail.com','1234','Ines',sysdate,sysdate,null,'1');

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active)
values (seq_clients.nextval,'filipe@gmail.com','1234','Filipe',sysdate,sysdate,null,'1');

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active)
values (seq_clients.nextval,'sarah@gmail.com','1234','Sarah',sysdate,sysdate,null,'1');

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active)
values (seq_clients.nextval,'barbara@gmail.com','1234','Barbara',sysdate,sysdate,null,'0');