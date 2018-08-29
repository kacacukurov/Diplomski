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


-- GRAPES
insert into grape(id, deleted, grape_name, version)
values(101, 0, 'Merlot', 1);
insert into grape(id, deleted, grape_name, version)
values(102, 0, 'Cabernet Sauvignon', 1);
insert into grape(id, deleted, grape_name, version)
values(103, 0, 'Cabernet Franc', 1);
insert into grape(id, deleted, grape_name, version)
values(104, 0, 'Petit Verdot', 1);
insert into grape(id, deleted, grape_name, version)
values(105, 0, 'Malbec', 1);
insert into grape(id, deleted, grape_name, version)
values(106, 0, 'Grenache', 1);
insert into grape(id, deleted, grape_name, version)
values(107, 0, 'Syrah', 1);
insert into grape(id, deleted, grape_name, version)
values(108, 0, 'Mourvedre', 1);
insert into grape(id, deleted, grape_name, version)
values(109, 0, 'Bourboulenc', 1);
insert into grape(id, deleted, grape_name, version)
values(110, 0, 'Cinsault', 1);
insert into grape(id, deleted, grape_name, version)
values(111, 0, 'Clairette Blanche', 1);
insert into grape(id, deleted, grape_name, version)
values(112, 0, 'Counoise', 1);
insert into grape(id, deleted, grape_name, version)
values(113, 0, 'Grenache Blanc', 1);
insert into grape(id, deleted, grape_name, version)
values(114, 0, 'Muscardin', 1);
insert into grape(id, deleted, grape_name, version)
values(115, 0, 'Picardan', 1);
insert into grape(id, deleted, grape_name, version)
values(116, 0, 'Piquepoul Blanc', 1);
insert into grape(id, deleted, grape_name, version)
values(117, 0, 'Roussanne', 1);
insert into grape(id, deleted, grape_name, version)
values(118, 0, 'Terret Noir', 1);
insert into grape(id, deleted, grape_name, version)
values(119, 0, 'Vaccarese', 1);
insert into grape(id, deleted, grape_name, version)
values(120, 0, 'Semillon', 1);
insert into grape(id, deleted, grape_name, version)
values(121, 0, 'Sauvignon Blanc', 1);
insert into grape(id, deleted, grape_name, version)
values(122, 0, 'Muscadelle', 1);
insert into grape(id, deleted, grape_name, version)
values(123, 0, 'Pinot Noir', 1);
insert into grape(id, deleted, grape_name, version)
values(124, 0, 'Gamay', 1);
insert into grape(id, deleted, grape_name, version)
values(125, 0, 'Chardonnay', 1);
insert into grape(id, deleted, grape_name, version)
values(126, 0, 'Aligote', 1);
insert into grape(id, deleted, grape_name, version)
values(127, 0, 'Marsanne', 1);
insert into grape(id, deleted, grape_name, version)
values(128, 0, 'Viognier', 1);
insert into grape(id, deleted, grape_name, version)
values(129, 0, 'Clairette', 1);
insert into grape(id, deleted, grape_name, version)
values(131, 0, 'Garganega', 1);
insert into grape(id, deleted, grape_name, version)
values(132, 0, 'Trebbiano', 1);
insert into grape(id, deleted, grape_name, version)
values(133, 0, 'Pinot Blanc', 1);
insert into grape(id, deleted, grape_name, version)
values(134, 0, 'Sangiovese', 1);
insert into grape(id, deleted, grape_name, version)
values(135, 0, 'Corvina', 1);
insert into grape(id, deleted, grape_name, version)
values(136, 0, 'Molinara', 1);
insert into grape(id, deleted, grape_name, version)
values(137, 0, 'Rondinella', 1);
insert into grape(id, deleted, grape_name, version)
values(138, 0, 'Tempranillo', 1);
insert into grape(id, deleted, grape_name, version)
values(139, 0, 'Mazuelo', 1);
insert into grape(id, deleted, grape_name, version)
values(140, 0, 'Gracino', 1);
insert into grape(id, deleted, grape_name, version)
values(141, 0, 'Maturana Tinta', 1);
insert into grape(id, deleted, grape_name, version)
values(142, 0, 'Viura', 1);
insert into grape(id, deleted, grape_name, version)
values(143, 0, 'Malvasia', 1);
insert into grape(id, deleted, grape_name, version)
values(144, 0, 'Verdejo', 1);
insert into grape(id, deleted, grape_name, version)
values(145, 0, 'Garnacha Blanca', 1);
insert into grape(id, deleted, grape_name, version)
values(146, 0, 'Carignan', 1);
insert into grape(id, deleted, grape_name, version)
values(147, 0, 'Carmenere', 1);
insert into grape(id, deleted, grape_name, version)
values(148, 0, 'Touriga Nacional', 1);
insert into grape(id, deleted, grape_name, version)
values(149, 0, 'Touriga Franca', 1);
insert into grape(id, deleted, grape_name, version)
values(150, 0, 'Tinta Roriz', 1);
insert into grape(id, deleted, grape_name, version)
values(151, 0, 'Tinto Cao', 1);
insert into grape(id, deleted, grape_name, version)
values(152, 0, 'Rolle', 1);
insert into grape(id, deleted, grape_name, version)
values(153, 0, 'Pinot Meunier', 1);
insert into grape(id, deleted, grape_name, version)
values(154, 0, 'Macabeo', 1);
insert into grape(id, deleted, grape_name, version)
values(155, 0, 'Parellada', 1);
insert into grape(id, deleted, grape_name, version)
values(156, 0, 'Xarello', 1);
insert into grape(id, deleted, grape_name, version)
values(157, 0, 'Tinta Barroca', 1);


-- WINES
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(101, 0, null, 1, 1, 2, 1, 'Red bordeaux', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(102, 0, null, 1, 1, 2, 1, 'Chateauneuf-du-pape', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(103, 0, null, 1, 1, 0, 1, 'White bordeaux', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(104, 0, null, 1, 1, 2, 1, 'Red bourgogne', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(105, 0, null, 1, 1, 0, 1, 'White bourgogne', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(106, 0, null, 1, 1, 2, 1, 'Red rhone', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(107, 0, null, 1, 1, 0, 1, 'White rhone', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(108, 0, null, 1, 1, 0, 1, 'Soave', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(109, 0, null, 1, 1, 2, 1, 'Chianti', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(110, 0, null, 1, 1, 2, 1, 'Super tuscan', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(111, 0, null, 1, 1, 2, 1, 'Amarone della valpolicella', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(112, 0, null, 1, 1, 2, 1, 'Rioja', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(113, 0, null, 1, 1, 0, 1, 'White rioja', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(114, 0, null, 1, 1, 2, 1, 'Priorat', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(115, 0, null, 1, 1, 2, 1, 'Meritage', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(116, 0, null, 1, 1, 2, 1, 'Port', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(117, 0, null, 1, 1, 1, 1, 'Provence rose', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(118, 0, null, 1, 1, 0, 1, 'Champagne', 1, null, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(119, 0, null, 1, 1, 0, 1, 'Cava', 1, null, null);
