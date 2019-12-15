
INSERT INTO `user_type` (`id_user_type`, `type`, `token`, `tx_user`, `tx_host`, `tx_date`) VALUES
(NULL, 'Administrador', '992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4', 'fer', 'localhost', '2019-11-22'),
(NULL, 'Usuario', NULL, 'fer', 'localhost', '2019-11-22');

INSERT INTO `transport_info` (`id_transport_info`, `tx_hosts`, `tx_user`, `tx_date`,`type`, `info_description`) VALUES
(NULL, 'loclahost', 'fer', '2019-11-15', '1',  'Puma Katari'),
(NULL, 'loclahost', 'fer', '2019-11-15', '1',  'Teleferico');


INSERT INTO `transport` (`id_transport`, `transport_status`, `tx_host`, `tx_user`, `tx_date`, `description`, `transport_info_id_transport_info`, `route_image`) VALUES
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Inca Llojeta', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Villa Salome', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutavillasalome.pdf'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Chasquipampa', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutachasquipampa.pdf'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Caja Ferroviaria', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/%20CAJA%20FERROVIARIA-02-02-02.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Integradora', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/Mapa%20Final%20Integradora%20Web-01.png'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Irpavi II', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/IRPAVI%20MAPA%20SOLO-02.pdf'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Achumani', '0', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/Mapa%20Oficial%20Achumani.pdf'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Naranja', '1', 'https://i.ibb.co/z8pWD7Q/Naranja.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Amarilla', '1', 'https://i.ibb.co/DMVmW6Z/Amarilla.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Azul', '1', 'https://i.ibb.co/cYs3qv1/Azul.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Blanca', '1', 'https://i.ibb.co/YWSH3dc/Blanca.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Cafe', '1', 'https://i.ibb.co/30PCx21/Cafe.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Celeste', '1', 'https://i.ibb.co/D8rRf7x/Celeste.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Morada', '1', 'https://i.ibb.co/x2T4zbN/Morada.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Plateada', '1', 'https://i.ibb.co/fM0FnxX/Plateada.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Roja', '1', 'https://i.ibb.co/HrjXVGJ/Rojo.jpg'),
(NULL, '1', 'localhost', 'fer', '2019-11-15', 'Linea Verde', '1', 'https://i.ibb.co/pfptz1S/Verde.jpg');

/*Datos paradas Telefericos*/
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
/*Insertando datos de puma katari Inca Llojeta-PUC*/
insert into `stop` (`id_stop`,`stop_status`,`tx_host`,`tx_user`,`tx_date`,`latitude`,`longitude`,`description`) values
('27','1','localhost','karen',now(),'-16.50394','-68.12842','P Cancha Zapata'),
('28','1','localhost','karen',now(),'-16.51426','-68.11445','P IV Centenario - U.E. Amor de Dios'),
('29','1','localhost','karen',now(),'-16.51684','-68.12144','P Puentes Trillizos'),
('30','1','localhost','karen',now(),'-16.51987','-68.12539','P Emaverde'),
('31','1','localhost','karen',now(),'-16.5231','-68.12198','R. Cementerio Japonés'),
('32','1','localhost','karen',now(),'-16.52578','-68.12371','P U.E. Boliviano Japonés'),
('33','1','localhost','karen',now(),'-16.5278','-68.12645','P H. Nuestra Sra. de La Paz'),
('34','1','localhost','karen',now(),'-16.53101','-68.12626','P Las Acacias'),
('35','1','localhost','karen',now(),'-16.52971','-68.12862','P Las Petunias'),
('36','1','localhost','karen',now(),'-16.53286','-68.13162','P Tomillo'),
('37','1','localhost','karen',now(),'-16.53394','-68.13461','P Vergelito'),
('38','1','localhost','karen',now(),'-16.53348','-68.13795','P Centro de Salud Vergel'),
('39','1','localhost','karen',now(),'-16.53315','-68.14037','P Final Buenos Aires'),
('40','1','localhost','karen',now(),'-16.53251','-68.14256','P U.E. José Santos Vargas'),
('41','1','localhost','karen',now(),'-16.53418','-68.14588','P Raúl Salmón'),
('42','1','localhost','karen',now(),'-16.52899','-68.14304','P R. Tupac Katari')
;
/*-------------------------------------------------*/
/*Rutas de teleferico*/
insert into `route` (`id_route`,`route_status`,`tx_host`,`tx_user`,`tx_date`,`route_name`,`route_details`,`stop_start`,`stop_finish`) values
('1','1','localhost','karen',now(),'Línea Azul','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1FR6QONhp60bSEgcK0QzGQDwRRBfjN8eJ]]>','1','5'),
('2','1','localhost','karen',now(),'Línea Roja','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1H-WxMl3P1-kQMekzmzXP6Anl4AfvQBof]]>','5','7'),
('3','1','localhost','karen',now(),'Linea Naranja','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1sjcfFIOb8UnjRZeESzFxihJSXBcP7k42]]>','7','10'),
('4','1','localhost','karen',now(),'Línea Blanca','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=13EEAKvIgW4qgG3peLE2_OHFzIZ7NykS9]]>','10','15'),
('5','1','localhost','karen',now(),'Línea Celeste','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1rFhlweyNsynr8g10ZQvNB8n3DVrK2yjN]]>','13','16'),
('6','1','localhost','karen',now(),'Línea Verde','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1BW18BkpcL1-ZvQit_0svDqABianW9zE0]]>','16','19'),
('7','1','localhost','karen',now(),'Línea Amarilla','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1-86oYxjF_jyJxQc3I5VhMjTNa0tuoKAv]]>','16','22'),
('8','1','localhost','karen',now(),'Línea Morada','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1vsibOWQr8OrDKAz2ekD08_q59AjA_UY4]]>','24','26'),
('9','1','localhost','karen',now(),'Línea Café','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1sjQ6xUw0oyyKZJuuD9aUWcFMjKGKSx_a]]>','11','23'),
('10','1','localhost','karen',now(),'Línea Plateada','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1osq23KE1YO2EeaT9_sS4syQhBiC5MZk8]]>','5','22');
/*Rutas PUMA Inca Llojeta-PUC*/
insert into `route` (`id_route`,`route_status`,`tx_host`,`tx_user`,`tx_date`,`route_name`,`route_details`,`stop_start`,`stop_finish`) values
('11','1','localhost','karen',now(),'Inca Llojeta - PUC','<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1alvQHnYnglrqJp_I86OIk4OWv6atPWTf]]>','27','42');



insert into `type_connection`(`id_typeconnection`,`typeconnection_status`,`tx_host`,`tx_user`,`tx_date`,`t_transport_a`,`t_transport_b`,`description`) values
('1','1','localhost','karen',now(),'2','2','Teleférico'),
('2','1','localhost','karen',now(),'1','1','Puma Katari'),
('3','1','localhost','karen',now(),'1','2','Teleférico-Puma Katari');

/*CONEXION DE RUTAAAAAAAAAAAS*/
insert into `connection_routes` (`id_coroutes`,`coroutes_status`,`tx_host`,`tx_user`,`tx_date`,`route_a`,`route_b`,`type_connection`,`description`) values
('1','1','localhost','karen',now(),'1','2','1','Linea Azul-Rojo'),
('2','1','localhost','karen',now(),'2','3','1','Linea Rojo-Naranja'),
('3','1','localhost','karen',now(),'3','4','1','Linea Naranja-Blanca'),
('4','1','localhost','karen',now(),'4','5','1','Linea Blanca-Celeste'),
('5','1','localhost','karen',now(),'5','6','1','Linea Celeste-Verde'),
('6','1','localhost','karen',now(),'5','7','1','Linea Celeste-Amarilla'),
('7','1','localhost','karen',now(),'7','6','1','Linea Verde-Amarilla'),
('8','1','localhost','karen',now(),'10','7','1','Linea Amarilla-Plateada'),
('9','1','localhost','karen',now(),'10','1','1','Linea Plateada-Azul'),
('10','1','localhost','karen',now(),'10','8','1','Linea Plateada-Morada'),
('11','1','localhost','karen',now(),'4','9','1','Linea Blanca-Cafe'),
('12','1','localhost','karen',now(),'10','2','1','Linea Plateada-Rojo');
/*CONEXION DE RUTAS DE PUMA KATARI*/
insert into `connection_routes` (`id_coroutes`,`coroutes_status`,`tx_host`,`tx_user`,`tx_date`,`route_a`,`route_b`,`type_connection`,`description`) values
('13','1','localhost','karen',now(),'5','11','3','Linea Azul-Rojo');



/*Conexion de telefericos*/
insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('1','1','1','karen','localhost',now()),
('2','1','2','karen','localhost',now()),
('3','1','3','karen','localhost',now()),
('4','1','4','karen','localhost',now()),
('5','1','5','karen','localhost',now()),

('6','2','5','karen','localhost',now()),
('7','2','6','karen','localhost',now()),
('8','2','7','karen','localhost',now()),

('9','3','7','karen','localhost',now()),
('10','3','8','karen','localhost',now()),
('11','3','9','karen','localhost',now()),
('12','3','10','karen','localhost',now()),

('13','4','10','karen','localhost',now()),
('14','4','11','karen','localhost',now()),
('15','4','12','karen','localhost',now()),
('16','4','15','karen','localhost',now()),

('17','5','13','karen','localhost',now()),
('18','5','14','karen','localhost',now()),
('19','5','15','karen','localhost',now()),
('20','5','16','karen','localhost',now()),

('21','6','16','karen','localhost',now()),
('22','6','17','karen','localhost',now()),
('23','6','18','karen','localhost',now()),
('24','6','19','karen','localhost',now()),

('25','7','16','karen','localhost',now()),
('26','7','20','karen','localhost',now()),
('27','7','21','karen','localhost',now()),
('28','7','22','karen','localhost',now()),

('29','8','24','karen','localhost',now()),
('30','8','25','karen','localhost',now()),
('31','8','26','karen','localhost',now()),

('32','9','11','karen','localhost',now()),
('33','9','23','karen','localhost',now()),

('34','10','5','karen','localhost',now()),
('35','10','25','karen','localhost',now()),
('36','10','22','karen','localhost',now());
/*Conexion de Pumas Kataris Inca Llojeta-PUC*/
insert into `route_stop` (`id_route_stop`,`route_id_route`,`stop_id_stop`,`tx_user`,`tx_host`,`tx_date`)values
('37','11','27','karen','localhost',now()),
('38','11','28','karen','localhost',now()),
('39','11','29','karen','localhost',now()),
('40','11','30','karen','localhost',now()),
('41','11','31','karen','localhost',now()),
('42','11','32','karen','localhost',now()),
('43','11','33','karen','localhost',now()),
('44','11','34','karen','localhost',now()),
('45','11','35','karen','localhost',now()),
('46','11','36','karen','localhost',now()),
('47','11','37','karen','localhost',now()),
('48','11','38','karen','localhost',now()),
('49','11','39','karen','localhost',now()),
('50','11','40','karen','localhost',now()),
('51','11','41','karen','localhost',now()),
('52','11','42','karen','localhost',now());




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



insert into `parada_taxi`(`id_taxi`, `company_name`,`phone_number`,`zone`, `address`,`tx_user`,`tx_host`,`tx_date`) values
(1,'Radio Movil Expreso Del Sur','2771818','Zona Sur','Alto Florida, Calle 1 Nº 26, La Paz','ccaverotx','localhost',now()),
(2,'Radio Taxi San Antonio','2222222','Zona Norte','Villa San Antonio','ccaverotx','localhost',now()),
(3,'Radio Taxi Magnate','63935165','Centro',' Av. Diego de Peralta, La Paz','ccaverotx','localhost',now());


UPDATE `transport` SET `route_image` = 'https://www.google.com/maps/d/embed?mid=1JsQc8sA0Nx-DOvIPTBzgwu4ZvcIFxatX' WHERE `transport`.`id_transport` = 0;
