
INSERT INTO `user_type` (`id_user_type`, `type`, `token`, `tx_user`, `tx_host`, `tx_date`) VALUES
(NULL, 'Administrador', '992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4', 'fer', 'localhost', '2019-11-22'),
(NULL, 'Usuario', NULL, 'fer', 'localhost', '2019-11-22');

INSERT INTO `transport_info` (`id_transport_info`, `tx_hosts`, `tx_user`, `tx_date`,`type`, `info_description`) VALUES
('0', 'loclahost', 'fer', '2019-11-15', '1',  'Puma Katari'),
('1', 'loclahost', 'fer', '2019-11-15', '1',  'Teleferico');


INSERT INTO `transport` (`id_transport`, `transport_status`, `tx_host`, `tx_user`, `tx_date`, `description`, `transport_info_id_transport_info`, `route_image`) VALUES
('0', '1', 'localhost', 'fer', '2019-11-15', 'Inca Llojeta', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf'),
('1', '1', 'localhost', 'fer', '2019-11-15', 'Villa Salome', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutavillasalome.pdf'),
('2', '1', 'localhost', 'fer', '2019-11-15', 'Chasquipampa', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutachasquipampa.pdf'),
('3', '1', 'localhost', 'fer', '2019-11-15', 'Caja Ferroviaria', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/%20CAJA%20FERROVIARIA-02-02-02.jpg'),
('4', '1', 'localhost', 'fer', '2019-11-15', 'Integradora', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/Mapa%20Final%20Integradora%20Web-01.png'),
('5', '1', 'localhost', 'fer', '2019-11-15', 'Irpavi II', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/IRPAVI%20MAPA%20SOLO-02.pdf'),
('6', '1', 'localhost', 'fer', '2019-11-15', 'Achumani', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/Mapa%20Oficial%20Achumani.pdf'),
('7', '1', 'localhost', 'fer', '2019-11-15', 'Linea Naranja', '1', 'https://i.ibb.co/z8pWD7Q/Naranja.jpg'),
('8', '1', 'localhost', 'fer', '2019-11-15', 'Linea Amarilla', '1', 'https://i.ibb.co/DMVmW6Z/Amarilla.jpg'),
('9', '1', 'localhost', 'fer', '2019-11-15', 'Linea Azul', '1', 'https://i.ibb.co/cYs3qv1/Azul.jpg'),
('10', '1', 'localhost', 'fer', '2019-11-15', 'Linea Blanca', '1', 'https://i.ibb.co/YWSH3dc/Blanca.jpg'),
('11', '1', 'localhost', 'fer', '2019-11-15', 'Linea Cafe', '1', 'https://i.ibb.co/30PCx21/Cafe.jpg'),
('12', '1', 'localhost', 'fer', '2019-11-15', 'Linea Celeste', '1', 'https://i.ibb.co/D8rRf7x/Celeste.jpg'),
('13', '1', 'localhost', 'fer', '2019-11-15', 'Linea Morada', '1', 'https://i.ibb.co/x2T4zbN/Morada.jpg'),
('14', '1', 'localhost', 'fer', '2019-11-15', 'Linea Plateada', '1', 'https://i.ibb.co/fM0FnxX/Plateada.jpg'),
('15', '1', 'localhost', 'fer', '2019-11-15', 'Linea Roja', '1', 'https://i.ibb.co/HrjXVGJ/Rojo.jpg'),
('16', '1', 'localhost', 'fer', '2019-11-15', 'Linea Verde', '1', 'https://i.ibb.co/pfptz1S/Verde.jpg');


insert into `stop` (`id_stop`,`stop_status`,`tx_host`,`tx_user`,`tx_date`,`latitude`,`longitude`,`description`) values
('1','1','localhost','karen',now(),'-16.48996','-68.20907','P Teleferico Río Seco'),
('2','1','localhost','karen',now(),'-16.48952','-68.19287','P Teleferico Plaza Upea'),
('3','1','localhost','karen',now(),'-16.49206','-68.18306','P Teleferico Plaza La Paz'),
('4','1','localhost','karen',now(),'-16.49478','-68.17354','P Teleferico Plaza 16 de julio'),
('5','1','localhost','karen',now(),'-16.49777','-68.16482','P Jacha Qhathu'),
('6','1','localhost','karen',now(),'-16.49814','-68.15293','P Teleferico Cementerio'),
('7','1','localhost','karen',now(),'-16.49121','-68.14444','P Teleferico Estacion Central'),
('8','1','localhost','karen',now(),'-16.49039','-68.13686','P Teleferico Armentia'),
('9','1','localhost','karen',now(),'-16.48672','-68.1313','P Teleferico Periferica'),
('10','1','localhost','karen',now(),'-16.48459','-68.1218','P Heroes de la Revolucion'),
('11','1','localhost','karen',now(),'-16.49367','-68.12133','P Teleferico Monumento a Busch'),
('12','1','localhost','karen',now(),'-16.50413','-68.12087','P Teleferico Plaza Triangular'),
('13','1','localhost','karen',now(),'-16.50061',' -68.13234','P Teleferico Prado'),
('14','1','localhost','karen',now(),'-16.50406','-68.12598','P Teleferico Teatro al Aire Libre'),
('15','1','localhost','karen',now(),'-16.51075','-68.12068','P Teleferico Av. del Poeta'),
('16','1','localhost','karen',now(),'-16.51934','-68.11619','P Teleferico del Libertador'),
('17','1','localhost','karen',now(),'-16.52147','-68.11036','P Teleferico Alto Obrajes'),
('18','1','localhost','karen',now(),'-16.52672','-68.10145','P Teleferico Obrajes'),
('19','1','localhost','karen',now(),'-16.53814','-68.08742','P Teleferico Irpavi'),
('20','1','localhost','karen',now(),'-16.51472','-68.13052','P Teleferico Sopocachi'),
('21','1','localhost','karen',now(),'-16.51567','-68.14578','P Teleferico Buenos Aires'),
('22','1','localhost','karen',now(),'-16.51858','-68.1503','P Teleferico Mirador'),
('23','1','localhost','karen',now(),'-16.49778','-68.115','P Teleferico Las Villas'),
('24','1','localhost','karen',now(),'-16.52266','-68.16935','P Teleferico Av. 6 de marzo'),
('25','1','localhost','karen',now(),'-16.51229','-68.15371','P Teleferico Faro Murillo'),
('26','1','localhost','karen',now(),'-16.50001','-68.13524','P Teleferico San Jose')
;

insert into `route` (`id_route`,`route_status`,`tx_host`,`tx_user`,`tx_date`,`route_name`,`route_details`,`stop_start`,`stop_finish`) values
('1','1','localhost','karen',now(),'Línea Rojo - Naranja','https://www.google.com/maps/d/u/0/viewer?hl=es&mid=1JLHwlh2OlCtwJ9ho0ckPqKw-x6s2SgO6&ll=-16.493947559882965%2C-68.14231482188961&z=15','5','10'),
('2','1','localhost','karen',now(),'Línea Celeste - Verde','https://www.google.com/maps/d/u/0/viewer?hl=es&mid=11XHcOPxq7CJtd3WFeXzPy10qu0b7btH3&ll=-16.519375911356633%2C-68.10987999999998&z=14','13','19'),
('3','1','localhost','karen',now(),'Línea Azul','https://www.google.com/maps/d/u/0/viewer?hl=es&mid=1FR6QONhp60bSEgcK0QzGQDwRRBfjN8eJ&ll=-16.49736880970751%2C-68.16818601801828&z=14','1','5'),
('4','1','localhost','karen',now(),'Linea Amarilla - Verde','https://www.google.com/maps/d/u/0/viewer?mid=1mP8gFLVrPvvwyfwk4TMiZi0GUQjbeGMs&hl=es&ll=-16.526430355059514%2C-68.11886000000004&z=14','22','19'),
('5','1','localhost','karen',now(),'Línea Naranja - Blanca - Celeste - Verde','https://www.google.com/maps/d/u/0/viewer?mid=1iOBUFy7ez8cOfV1RMOG959XPfOLUKt_L&hl=es&ll=-16.511950499369622%2C-68.10746449649912&z=14','7','19');

insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('1','1','5','karen','localhost',now()),
('2','1','6','karen','localhost',now()),
('3','1','7','karen','localhost',now()),
('4','1','8','karen','localhost',now()),
('5','1','9','karen','localhost',now()),
('6','1','10','karen','localhost',now()),
('7','2','13','karen','localhost',now()),
('8','2','14','karen','localhost',now()),
('9','2','15','karen','localhost',now()),
('10','2','16','karen','localhost',now()),
('11','2','17','karen','localhost',now()),
('12','2','18','karen','localhost',now()),
('13','2','19','karen','localhost',now()),
('14','3','1','karen','localhost',now()),
('15','3','2','karen','localhost',now()),
('16','3','3','karen','localhost',now()),
('17','3','4','karen','localhost',now()),
('18','3','5','karen','localhost',now()),
('19','4','22','karen','localhost',now()),
('20','4','21','karen','localhost',now()),
('21','4','20','karen','localhost',now()),
('22','4','16','karen','localhost',now()),
('23','4','17','karen','localhost',now()),
('24','4','18','karen','localhost',now()),
('25','4','19','karen','localhost',now()),
('26','5','7','karen','localhost',now()),
('27','5','8','karen','localhost',now()),
('28','5','9','karen','localhost',now()),
('29','5','10','karen','localhost',now()),
('30','5','11','karen','localhost',now()),
('31','5','12','karen','localhost',now()),
('32','5','15','karen','localhost',now()),
('33','5','16','karen','localhost',now()),
('34','5','17','karen','localhost',now()),
('35','5','18','karen','localhost',now()),
('36','5','19','karen','localhost',now());

insert into `exception` (`id_exception`, `question_message`, `answer_message`,`tx_user`,`tx_host`,`tx_date`) values
(1,'Pregunta 1','Respuesta 1','ccaverotx','localhost',now()),
(2,'Pregunta 2','Respuesta 2','ccaverotx','localhost',now()),
(3,'Pregunta 3','Respuesta 3','ccaverotx','localhost',now()),
(4,'Pregunta 4','Respuesta 4','ccaverotx','localhost',now());

UPDATE `exception` SET `answer_message` = 'https://www.google.com/maps/d/embed?mid=16zO-9hCUuupQotNH0PLxqaCHkTyPy53Y' WHERE `exception`.`id_exception` = 1;

/* show fields from 'table_name'

       SELECT table_name FROM information_schema.columns WHERE (table_schema='lpbus_bot' and ordinal_position=1 )
*/

/* CAMBIEN ESTOS DATOS PORFA*/

UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=16zO-9hCUuupQotNH0PLxqaCHkTyPy53Y' WHERE `transport`.`id_transport` = 16;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1osz2LmudoqBe784v_8H53NbgXEkRSOPp' WHERE `transport`.`id_transport` = 15;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1pc8dC5aG9bFKQyk238RAUAmIKCdub-Op' WHERE `transport`.`id_transport` = 14;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1ylisSj1I8nVr1hTENTd9KQoN8tGpPp0t' WHERE `transport`.`id_transport` = 13;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1xlLZ1l2OykTpaeyluBflkR12LnnNhw43' WHERE `transport`.`id_transport` = 12;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1smy2gc-wzjMlOyP_Vd4viqVo6sHudq1E' WHERE `transport`.`id_transport` = 11;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1j953ojotqhF6o6zIeVKeEuTJe6jhiWue' WHERE `transport`.`id_transport` = 10;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=17P2EM-3_sdyOFiEB3otH2I-n9-Vg9q4C' WHERE `transport`.`id_transport` = 9;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1nWRd7tA0oB_kmUl3YCWrtqSiPlidxBc1' WHERE `transport`.`id_transport` = 8;
UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1XwP_S5xdWhd_CwpjcBMdues8D0GzwslR' WHERE `transport`.`id_transport` = 7;



