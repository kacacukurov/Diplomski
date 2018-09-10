-- ROLES
insert into authority (id, type) values (1, 'ADMIN');
insert into authority (id, type) values (2, 'USER');


-- ADMINISTRATORS
  -- account admin 1  pass:admin123
insert into account (id, deleted, password, username, first_name, last_name, version)
values (101, 0, '$2a$10$cufIyhl6IuaxoWaNcGWgnuWaKYvtZnfvqGHi5J3O25XQHSB592SOS','admin@gmail.com', 'Admin', 'Adminsky', 0);
insert into account_authority(id, account_id, authority_id) values (101, 101, 1);
-- USER
  -- account user 1 pass:user123
insert into account (id, deleted, password, username, first_name, last_name, version)
values (102, 0, '$2a$04$90Eevy7OcQYKZ4QJEcm0vuXNIh1atOjvv2KAmHovNVVF4jRQEZoJW','user@gmail.com', 'User', 'Usersky', 0);
insert into account_authority(id, account_id, authority_id) values (102, 102, 2);


--  WINERIES
insert into winery(id, deleted, version, winery_name)
values(101, 0, 1, 'Bancroft');
insert into winery(id, deleted, version, winery_name)
values(102, 0, 1, 'Chateau Margaux Winery');
insert into winery(id, deleted, version, winery_name)
values(103, 0, 1, 'Beringer');
insert into winery(id, deleted, version, winery_name)
values(104, 0, 1, 'Congress Springs');
insert into winery(id, deleted, version, winery_name)
values(105, 0, 1, 'D Anjou');
insert into winery(id, deleted, version, winery_name)
values(106, 0, 1, 'Foxen');
insert into winery(id, deleted, version, winery_name)
values(107, 0, 1, 'Kathryn Kennedy');
insert into winery(id, deleted, version, winery_name)
values(108, 0, 1, 'Longridge');
insert into winery(id, deleted, version, winery_name)
values(109, 0, 1, 'Santa Cruz Mountain Vineyard');

--  REGIONS
insert into region(id, deleted, locatedin, region_name, version)
values(101, 0, null, 'US region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(102, 0, null, 'French region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(103, 0, null, 'Italian region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(104, 0, 109, 'Medoc region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(105, 0, 101, 'California region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(106, 0, 105, 'Napa region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(107, 0, 105, 'Santa Cruz Mountains region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(108, 0, 102, 'Bourgogne region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(109, 0, 102, 'Bordeaux region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(110, 0, 103, 'Chianti region', 1);
insert into region(id, deleted, locatedin, region_name, version)
values(111, 0, null, 'Spain region', 1);

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
values(101, 0, null, 1, 1, 2, 1, 'Red bordeaux', 0, 102, 105);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(102, 0, null, 1, 2, 2, 2, 'Chateauneuf-du-pape', 2, 102, 105);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(103, 0, null, 1, 2, 0, 0, 'White bordeaux', 0, 102, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(104, 0, null, 1, 0, 2, 1, 'Red bourgogne', 1, 102, 105);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(105, 0, null, 1, 0, 0, 1, 'White bourgogne', 1, 102, 105);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(106, 0, null, 1, 2, 2, 2, 'Red rhone', 1, 101, 107);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(107, 0, null, 1, 0, 0, 1, 'White rhone', 1, 101, 107);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(108, 0, null, 1, 0, 0, 0, 'Soave', 2, 103, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(109, 0, null, 1, 2, 2, 2, 'Chianti', 2, 103, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(110, 0, null, 1, 2, 2, 2, 'Super tuscan', 0, 103, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(111, 0, null, 1, 2, 2, 2, 'Amarone della valpolicella', 0, 103, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(112, 0, null, 1, 2, 2, 1, 'Rioja', 1, 111, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(113, 0, null, 1, 2, 0, 2, 'White rioja', 2, 111, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(114, 0, null, 1, 1, 2, 1, 'Priorat', 1, 111, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(115, 0, null, 1, 1, 2, 0, 'Meritage', 2, 101, 109);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(116, 0, null, 1, 2, 2, 2, 'Port', 0, 111, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(117, 0, null, 1, 1, 1, 1, 'Provence rose', 1, 102, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(118, 0, null, 1, 0, 0, 0, 'Champagne', 1, 102, null);
insert into wine(id, deleted, subclass_of_wine, version, wine_body, wine_color, wine_flavor, wine_name, wine_sugar, region_id, winery_id)
values(119, 0, null, 1, 1, 0, 1, 'Cava', 2, 111, null);


-- WINE_GRAPES
  -- Chateauneuf-du-pape
insert into wine_grapes(wine_id, grapes_id)
values(102, 106);
insert into wine_grapes(wine_id, grapes_id)
values(102, 107);
insert into wine_grapes(wine_id, grapes_id)
values(102, 108);
insert into wine_grapes(wine_id, grapes_id)
values(102, 109);
insert into wine_grapes(wine_id, grapes_id)
values(102, 110);
insert into wine_grapes(wine_id, grapes_id)
values(102, 111);
insert into wine_grapes(wine_id, grapes_id)
values(102, 112);
insert into wine_grapes(wine_id, grapes_id)
values(102, 113);
insert into wine_grapes(wine_id, grapes_id)
values(102, 114);
insert into wine_grapes(wine_id, grapes_id)
values(102, 115);
insert into wine_grapes(wine_id, grapes_id)
values(102, 116);
insert into wine_grapes(wine_id, grapes_id)
values(102, 117);
insert into wine_grapes(wine_id, grapes_id)
values(102, 118);
insert into wine_grapes(wine_id, grapes_id)
values(102, 119);
  -- Red bordeaux
insert into wine_grapes(wine_id, grapes_id)
values(101, 101);
insert into wine_grapes(wine_id, grapes_id)
values(101, 102);
insert into wine_grapes(wine_id, grapes_id)
values(101, 103);
insert into wine_grapes(wine_id, grapes_id)
values(101, 104);
insert into wine_grapes(wine_id, grapes_id)
values(101, 105);
  -- White bordeaux
insert into wine_grapes(wine_id, grapes_id)
values(103, 120);
insert into wine_grapes(wine_id, grapes_id)
values(103, 121);
insert into wine_grapes(wine_id, grapes_id)
values(103, 122);
  -- Red bourgogne
insert into wine_grapes(wine_id, grapes_id)
values(104, 123);
insert into wine_grapes(wine_id, grapes_id)
values(104, 124);
  -- White bourgogne
insert into wine_grapes(wine_id, grapes_id)
values(105, 125);
insert into wine_grapes(wine_id, grapes_id)
values(105, 126);
  -- Red rhone
insert into wine_grapes(wine_id, grapes_id)
values(106, 106);
insert into wine_grapes(wine_id, grapes_id)
values(106, 107);
insert into wine_grapes(wine_id, grapes_id)
values(106, 108);
  -- White rhone
insert into wine_grapes(wine_id, grapes_id)
values(107, 127);
insert into wine_grapes(wine_id, grapes_id)
values(107, 117);
insert into wine_grapes(wine_id, grapes_id)
values(107, 128);
insert into wine_grapes(wine_id, grapes_id)
values(107, 129);
insert into wine_grapes(wine_id, grapes_id)
values(107, 113);
insert into wine_grapes(wine_id, grapes_id)
values(107, 109);
  -- Soave
insert into wine_grapes(wine_id, grapes_id)
values(108, 131);
insert into wine_grapes(wine_id, grapes_id)
values(108, 132);
insert into wine_grapes(wine_id, grapes_id)
values(108, 125);
insert into wine_grapes(wine_id, grapes_id)
values(108, 133);
  -- Chianti
insert into wine_grapes(wine_id, grapes_id)
values(109, 134);
insert into wine_grapes(wine_id, grapes_id)
values(109, 102);
insert into wine_grapes(wine_id, grapes_id)
values(109, 103);
  -- Super tuscan
insert into wine_grapes(wine_id, grapes_id)
values(110, 101);
insert into wine_grapes(wine_id, grapes_id)
values(110, 102);
insert into wine_grapes(wine_id, grapes_id)
values(110, 134);
insert into wine_grapes(wine_id, grapes_id)
values(110, 103);
insert into wine_grapes(wine_id, grapes_id)
values(110, 107);
  -- Amarone della valpolicella
insert into wine_grapes(wine_id, grapes_id)
values(111, 135);
insert into wine_grapes(wine_id, grapes_id)
values(111, 136);
insert into wine_grapes(wine_id, grapes_id)
values(111, 137);
  -- Rioja
insert into wine_grapes(wine_id, grapes_id)
values(112, 138);
insert into wine_grapes(wine_id, grapes_id)
values(112, 139);
insert into wine_grapes(wine_id, grapes_id)
values(112, 140);
insert into wine_grapes(wine_id, grapes_id)
values(112, 141);
  -- White rioja
insert into wine_grapes(wine_id, grapes_id)
values(113, 142);
insert into wine_grapes(wine_id, grapes_id)
values(113, 143);
insert into wine_grapes(wine_id, grapes_id)
values(113, 144);
insert into wine_grapes(wine_id, grapes_id)
values(113, 145);
  -- Priorat
insert into wine_grapes(wine_id, grapes_id)
values(114, 106);
insert into wine_grapes(wine_id, grapes_id)
values(114, 107);
insert into wine_grapes(wine_id, grapes_id)
values(114, 146);
insert into wine_grapes(wine_id, grapes_id)
values(114, 101);
insert into wine_grapes(wine_id, grapes_id)
values(114, 102);
  -- Meritage
insert into wine_grapes(wine_id, grapes_id)
values(115, 101);
insert into wine_grapes(wine_id, grapes_id)
values(115, 102);
insert into wine_grapes(wine_id, grapes_id)
values(115, 103);
insert into wine_grapes(wine_id, grapes_id)
values(115, 104);
insert into wine_grapes(wine_id, grapes_id)
values(115, 105);
insert into wine_grapes(wine_id, grapes_id)
values(115, 147);
  -- Port
insert into wine_grapes(wine_id, grapes_id)
values(116, 148);
insert into wine_grapes(wine_id, grapes_id)
values(116, 149);
insert into wine_grapes(wine_id, grapes_id)
values(116, 150);
insert into wine_grapes(wine_id, grapes_id)
values(116, 151);
insert into wine_grapes(wine_id, grapes_id)
values(116, 157);
  -- Provence rose
insert into wine_grapes(wine_id, grapes_id)
values(117, 110);
insert into wine_grapes(wine_id, grapes_id)
values(117, 106);
insert into wine_grapes(wine_id, grapes_id)
values(117, 107);
insert into wine_grapes(wine_id, grapes_id)
values(117, 152);
  -- Champagne
insert into wine_grapes(wine_id, grapes_id)
values(118, 125);
insert into wine_grapes(wine_id, grapes_id)
values(118, 153);
insert into wine_grapes(wine_id, grapes_id)
values(118, 123);
  -- Cava
insert into wine_grapes(wine_id, grapes_id)
values(119, 154);
insert into wine_grapes(wine_id, grapes_id)
values(119, 155);
insert into wine_grapes(wine_id, grapes_id)
values(119, 156);
insert into wine_grapes(wine_id, grapes_id)
values(119, 125);
