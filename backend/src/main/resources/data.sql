-- ROLES
insert into authority (id, type) values (1, 'ADMIN');


-- ADMINISTRATORS
  -- account admin 1
insert into account (id, deleted, password, username, first_name, last_name, version)
values (101, 0, '$2a$04$NfYZ1tb6cuAQl.DNL76FjeHVXNmiMFtXlA8YWmOpg5H4lcF8jVnlS','kantagara@gmail.com', 'Nikola', 'Garabandic', 0);
insert into account_authority(id, account_id, authority_id) values (101, 101, 1);
  -- account admin 2
insert into account (id, deleted, password, username, first_name, last_name, version)
values (102, 0, '$2a$04$Ie/vN0kYNCWIHU5dwRdRp.KraHKu18S3oXPGjuZPVOQVtIjyniBrK', 'kaca.cukurov@gmail.com', 'Katarina', 'Cukurov', 0);
insert into account_authority(id, account_id, authority_id) values (102, 102, 1);
