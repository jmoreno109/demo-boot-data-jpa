insert into clients (name,last_name,email,dt_creation,photo) values ('pepe','santos','pepe@correo.com','2020-01-01','');
insert into clients (name,last_name,email,dt_creation,photo) values ('diego','santos','diego@correo.com','2020-01-01','');
insert into clients (name,last_name,email,dt_creation,photo) values ('juan','santos','juan@correo.com','2020-01-01','');

	
insert into products (name,price,dt_creation) values ('sony',12345,NOW());
insert into products (name,price,dt_creation) values ('panasonic',134679,NOW());
insert into products (name,price,dt_creation) values ('lg',124578,NOW());


insert into invoices (description,observation,dt_creation,client_id) values ('Factura equipos oficina','nuevo',NOW(),1);


insert into invoice_item (quantity,product_id,invoice_id) values (1,1,1);


