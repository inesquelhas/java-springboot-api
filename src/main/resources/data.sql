insert into roles (role_id, description) values (1, 'ROLE_ADMIN');
insert into roles (role_id, description) values (2, 'ROLE_USER');
insert into roles (role_id, description) values (3, 'ROLE_CLIENT');
insert into roles (role_id, description) values (4, 'ROLE_PARTNER');


insert into addresses (address_id, street_name, street_complement, creation_at, updated_at)
values (seq_addresses.nextval,'rua da mina','n 10',sysdate,null);

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active,address_id)
values (seq_clients.nextval,'ines@gmail.com','1234','Ines',sysdate,sysdate,null,'1',1);

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active,address_id)
values (seq_clients.nextval,'filipe@gmail.com','1234','Filipe',sysdate,sysdate,null,'1',1);

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active,address_id)
values (seq_clients.nextval,'sarah@gmail.com','1234','Sarah',sysdate,sysdate,null,'1',1);

insert into clients (client_id, email,password,name,birth_date,creation_at,updated_at,active,address_id)
values (seq_clients.nextval,'barbara@gmail.com','1234','Barbara',sysdate,sysdate,null,'0',1);