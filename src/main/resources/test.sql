INSERT INTO `transport_info` (`id_transport_info`, `tx_hosts`, `tx_user`, `tx_date`, `info_description`) VALUES
('0', 'loclahost', 'fer', '2019-11-15', 'Puma Katari'),
('1', 'loclahost', 'fer', '2019-11-15', 'Teleferico');


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
('1','1','localhost','karen',now(),'-16.495920','-68.132994','Parada Murillo'),
('2','1','localhost','karen',now(),'-16.490125','-68.137718','Parada Armentia'),
('3','1','localhost','karen',now(),'-16.490294','-68.136571','Parada Tel Naranja'),
('4','1','localhost','karen',now(),'-16.488641','-68.141014','Parada Terminal'),
('5','1','localhost','karen',now(),'-16.491265','-68.135366','Parada Plaza Riosiño'),
('6','1','localhost','karen',now(),'-16.545292','-68.085337','Parada Costanera'),
('7','1','localhost','karen',now(),'-16.537792','-68.088295','Parada Teleferico Verde'),
('8','1','localhost','karen',now(),'-16.534960','-68.084307','Parada Colegio Militar'),
('9','1','localhost','karen',now(),'-16.528087','-68.087335','Parada Irpavi'),
('10','1','localhost','karen',now(),'-16.541203','-68.091129','Parada Jardin Japones');

insert into `route` (`id_route`,`route_status`,`tx_host`,`tx_user`,`tx_date`,`route_details`,`stop_start`,`stop_finish`) values
('1','1','localhost','karen',now(),'-16.495920','1','2'),
('2','1','localhost','karen',now(),'-16.490125','1','3'),
('3','1','localhost','karen',now(),'-16.490294','1','9'),
('4','1','localhost','karen',now(),'-16.488641','2','8'),
('5','1','localhost','karen',now(),'-16.491265','3','7'),
('6','1','localhost','karen',now(),'-16.545292','4','10'),
('7','1','localhost','karen',now(),'-16.537792','5','7'),
('8','1','localhost','karen',now(),'-16.534960','3','10'),
('9','1','localhost','karen',now(),'-16.528087','2','9'),
('10','1','localhost','karen',now(),'-16.541203','1','10');

insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('1','9','3','karen','localhost',now()),
('2','9','1','karen','localhost',now());
insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('3','9','7','karen','localhost',now()),
('4','9','8','karen','localhost',now());