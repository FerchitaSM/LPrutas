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
('1','1','localhost','karen',now(),'-16.4943776','-68.2063204','P Teleferico Río Seco'),
('2','1','localhost','karen',now(),'-16.4947068','-68.1951625','P Teleferico Plaza Upea'),
('3','1','localhost','karen',now(),'-16.4968466','-68.1814725','P Teleferico Plaza La Paz'),
('4','1','localhost','karen',now(),'-16.4968466','-68.1814725','P Teleferico Plaza 16 de julio'),
('5','1','localhost','karen',now(),'-16.4997681','-68.1670958','P Jacha Qhathu'),
('6','1','localhost','karen',now(),'-16.4993566','-68.1527621','P Teleferico Cementerio'),
('7','1','localhost','karen',now(),'-16.537792','-68.088295','P Teleferico Estacion Central'),
('8','1','localhost','karen',now(),'-16.4905403','-68.1363577','P Teleferico Armentia'),
('9','1','localhost','karen',now(),'-16.4913326','-68.1370122','P Teleferico Periferica'),
('10','1','localhost','karen',now(),'-16.4915066','-68.1280661','P Heroes de la Revolucion'),
('11','1','localhost','karen',now(),'-16.4938109','-68.1272507','P Teleferico Monumento a Busch'),
('12','1','localhost','karen',now(),'-16.5012177','-68.1250191','P Teleferico Plaza Triangular'),
('13','1','localhost','karen',now(),'-16.5096058','-68.1330415','P Teleferico Prado'),
('14','1','localhost','karen',now(),'-16.504277','-68.125896','P Teleferico Teatro al Aire Libre'),
('15','1','localhost','karen',now(),'-16.5088031','-68.1231924','P Teleferico Av. del Poeta'),
('16','1','localhost','karen',now(),'-16.5157361','-68.118064','P Teleferico del Libertador'),
('17','1','localhost','karen',now(),'-16.521496','-68.110211','P Teleferico Alto Obrajes'),
('18','1','localhost','karen',now(),'-16.526989','-68.100640','P Teleferico Obrajes'),
('19','1','localhost','karen',now(),'-16.538227','-68.087339','P Teleferico Irpavi'),
('20','1','localhost','karen',now(),'-16.514775','-68.130338','P Teleferico Sopocachi'),
('21','1','localhost','karen',now(),'-16.515701','-68.145637','P Teleferico Buenos Aires'),
('22','1','localhost','karen',now(),'-16.518416','-68.149993','P Teleferico Mirador'),
('23','1','localhost','karen',now(),'-16.497986','-68.114931','P Teleferico Las Villas'),
('24','1','localhost','karen',now(),'-16.522795','-68.169675','P Teleferico Av. 6 de marzo'),
('25','1','localhost','karen',now(),'-16.512365','-68.153624','P Teleferico Faro Murillo'),
('26','1','localhost','karen',now(),'-16.500165','-68.135214','P Teleferico Almirante Grau')
;

insert into `route` (`id_route`,`route_status`,`tx_host`,`tx_user`,`tx_date`,`route_details`,`stop_start`,`stop_finish`) values
('1','1','localhost','karen',now(),'Línea Rojo - Naranja','5','10'),
('2','1','localhost','karen',now(),'Línea Celeste - Verde','13','19'),
('3','1','localhost','karen',now(),'Línea Azul','1','5'),
('4','1','localhost','karen',now(),'Linea Amarilla - Verde','22','19'),
('5','1','localhost','karen',now(),'Línea Naranja - Blanca - Celeste - Verde','7','19');

insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('1','1','6','karen','localhost',now()),
('2','1','7','karen','localhost',now()),
('3','1','8','karen','localhost',now()),
('4','1','9','karen','localhost',now()),
('5','2','14','karen','localhost',now()),
('6','2','15','karen','localhost',now()),
('7','2','16','karen','localhost',now()),
('8','2','17','karen','localhost',now()),
('9','2','18','karen','localhost',now()),
('10','3','2','karen','localhost',now()),
('11','3','3','karen','localhost',now()),
('12','3','4','karen','localhost',now()),
('13','4','21','karen','localhost',now()),
('14','4','20','karen','localhost',now()),
('15','4','16','karen','localhost',now()),
('16','4','17','karen','localhost',now()),
('17','4','18','karen','localhost',now()),
('18','5','8','karen','localhost',now()),
('19','5','9','karen','localhost',now()),
('20','5','10','karen','localhost',now()),
('21','5','11','karen','localhost',now()),
('22','5','12','karen','localhost',now()),
('23','5','15','karen','localhost',now()),
('24','5','16','karen','localhost',now()),
('25','5','17','karen','localhost',now()),
('26','5','18','karen','localhost',now());

insert into `route_stop_transport` (`id_route_stop_transport`,`transport_id_transport` ,`route_id_route` ,`tx_host` ,`tx_user`,`tx_date`) values
('1', '1','1','localhost', 'karen', '2019-11-24'),
('2', '1','2','localhost', 'karen', '2019-11-24'),
('3', '1','3','localhost', 'karen', '2019-11-24'),
('4', '1','4','localhost', 'karen', '2019-11-24'),
('5', '1','5','localhost', 'karen', '2019-11-24')
;