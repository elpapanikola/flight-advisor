insert into roles (id, role) values (1, 'ADMIN');
insert into roles (id, role) values (2, 'USER');

insert into user (id,first_name, last_name, username, password)  values (1,'admin','admin', 'admin','$2a$10$wmxeO5S9uvktNo.7j70kCueJa9Ti6PAlMmjllYsGqrytIUyxtnE5C');

insert into user_role (user_id, role_id) values (1,1);

commit;

