
CREATE TABLE `connection_routes` (
  `id_coroutes` int(11) NOT NULL,
  `coroutes_status` int(11) DEFAULT NULL,
  `tx_host` varchar(200) DEFAULT NULL,
  `tx_user` varchar(200) DEFAULT NULL,
  `tx_date` date DEFAULT NULL,
  `route_a` int(11) DEFAULT NULL,
  `route_b` int(11) DEFAULT NULL,
  `type_connection` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exception`
--

CREATE TABLE `exception` (
  `id_exception` int(11) NOT NULL,
  `question_message` varchar(400) NOT NULL,
  `answer_message` varchar(400) NOT NULL,
  `tx_user` varchar(50) NOT NULL,
  `tx_host` varchar(100) NOT NULL,
  `tx_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `exception`
--

INSERT INTO `exception` (`id_exception`, `question_message`, `answer_message`, `tx_user`, `tx_host`, `tx_date`) VALUES
(1, '¿Para que me sirve el chat?', 'Este chat es una guia para las personas que no conoscan muy bien como moverse de manera segura en la ciudad de La Paz', 'ccaverotx', 'localhost', '2019-12-10'),
(2, '¿Porque aveces no encuentra una ruta?', 'Las rutas se generan en base a sus pedidos, si es que desea ir por algun teleferico y no existe uno que lo lleve o lo deje cerca, le aparece que no existe una buena ruta', 'ccaverotx', 'localhost', '2019-12-10'),
(3, '¿Que hago si mi chat no me responde?\r\n', 'Si por algun motivo pasara, ten calma y espera un poco, el chat esta verificando la mejor ruta a tu destino, por lo cual tal vez tarde un poco', 'ccaverotx', 'localhost', '2019-12-10'),
(4, 'Otra pregunta', 'Comunicate con nosotros! Ingresa al grupo \r\n https://t.me/joinchat/Kl1fbBXDJVwq8uwJ6rcNNA', 'ccaverotx', 'localhost', '2019-12-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` int(11) NOT NULL,
  `name` text NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `name`, `latitude`, `longitude`) VALUES
(1, 'Casa Prado', -16.5022, -68.132),
(2, 'Casa Prado', -16.5022, -68.132);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parada_taxi`
--

CREATE TABLE `parada_taxi` (
  `id_taxi` int(11) NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `zone_t` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `map_photo` text NOT NULL,
  `tx_user` varchar(50) NOT NULL,
  `tx_host` varchar(100) NOT NULL,
  `tx_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ride_data`
--

CREATE TABLE `ride_data` (
  `id_ride` int(11) NOT NULL,
  `users_id_user` int(11) NOT NULL,
  `transport_id_transport` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `route_id_route` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route`
--

CREATE TABLE `route` (
  `id_route` int(11) NOT NULL,
  `route_status` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `route_name` varchar(200) NOT NULL,
  `route_details` varchar(200) NOT NULL,
  `stop_start` int(11) NOT NULL,
  `stop_finish` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `route`
--

INSERT INTO `route` (`id_route`, `route_status`, `tx_host`, `tx_user`, `tx_date`, `route_name`, `route_details`, `stop_start`, `stop_finish`) VALUES
(1, 1, 'localhost', 'karen', '2019-12-10', 'Línea Azul', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1FR6QONhp60bSEgcK0QzGQDwRRBfjN8eJ]]>', 1, 5),
(2, 1, 'localhost', 'karen', '2019-12-10', 'Línea Roja', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1H-WxMl3P1-kQMekzmzXP6Anl4AfvQBof]]>', 5, 7),
(3, 1, 'localhost', 'karen', '2019-12-10', 'Linea Naranja', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1sjcfFIOb8UnjRZeESzFxihJSXBcP7k42]]>', 7, 10),
(4, 1, 'localhost', 'karen', '2019-12-10', 'Línea Blanca', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=13EEAKvIgW4qgG3peLE2_OHFzIZ7NykS9]]>', 10, 15),
(5, 1, 'localhost', 'karen', '2019-12-10', 'Línea Celeste', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1rFhlweyNsynr8g10ZQvNB8n3DVrK2yjN]]>', 13, 16),
(6, 1, 'localhost', 'karen', '2019-12-10', 'Línea Verde', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1BW18BkpcL1-ZvQit_0svDqABianW9zE0]]>', 16, 19),
(7, 1, 'localhost', 'karen', '2019-12-10', 'Línea Amarilla', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1-86oYxjF_jyJxQc3I5VhMjTNa0tuoKAv]]>', 16, 22),
(8, 1, 'localhost', 'karen', '2019-12-10', 'Línea Morada', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1vsibOWQr8OrDKAz2ekD08_q59AjA_UY4]]>', 24, 26),
(9, 1, 'localhost', 'karen', '2019-12-10', 'Línea Café', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1sjQ6xUw0oyyKZJuuD9aUWcFMjKGKSx_a]]>', 11, 23),
(10, 1, 'localhost', 'karen', '2019-12-10', 'Línea Plateada', '<![CDATA[http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1osq23KE1YO2EeaT9_sS4syQhBiC5MZk8]]>', 5, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route_stop`
--

CREATE TABLE `route_stop` (
  `id_route_stop` int(11) NOT NULL,
  `route_id_route` int(11) NOT NULL,
  `stop_id_stop` int(11) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `route_stop`
--

INSERT INTO `route_stop` (`id_route_stop`, `route_id_route`, `stop_id_stop`, `tx_user`, `tx_host`, `tx_date`) VALUES
(1, 1, 1, 'karen', 'localhost', '2019-12-10'),
(2, 1, 2, 'karen', 'localhost', '2019-12-10'),
(3, 1, 3, 'karen', 'localhost', '2019-12-10'),
(4, 1, 4, 'karen', 'localhost', '2019-12-10'),
(5, 1, 5, 'karen', 'localhost', '2019-12-10'),
(6, 2, 5, 'karen', 'localhost', '2019-12-10'),
(7, 2, 6, 'karen', 'localhost', '2019-12-10'),
(8, 2, 7, 'karen', 'localhost', '2019-12-10'),
(9, 3, 7, 'karen', 'localhost', '2019-12-10'),
(10, 3, 8, 'karen', 'localhost', '2019-12-10'),
(11, 3, 9, 'karen', 'localhost', '2019-12-10'),
(12, 3, 10, 'karen', 'localhost', '2019-12-10'),
(13, 4, 10, 'karen', 'localhost', '2019-12-10'),
(14, 4, 11, 'karen', 'localhost', '2019-12-10'),
(15, 4, 12, 'karen', 'localhost', '2019-12-10'),
(16, 4, 15, 'karen', 'localhost', '2019-12-10'),
(17, 5, 13, 'karen', 'localhost', '2019-12-10'),
(18, 5, 14, 'karen', 'localhost', '2019-12-10'),
(19, 5, 15, 'karen', 'localhost', '2019-12-10'),
(20, 5, 16, 'karen', 'localhost', '2019-12-10'),
(21, 6, 16, 'karen', 'localhost', '2019-12-10'),
(22, 6, 17, 'karen', 'localhost', '2019-12-10'),
(23, 6, 18, 'karen', 'localhost', '2019-12-10'),
(24, 6, 19, 'karen', 'localhost', '2019-12-10'),
(25, 7, 16, 'karen', 'localhost', '2019-12-10'),
(26, 7, 20, 'karen', 'localhost', '2019-12-10'),
(27, 7, 21, 'karen', 'localhost', '2019-12-10'),
(28, 7, 22, 'karen', 'localhost', '2019-12-10'),
(29, 8, 24, 'karen', 'localhost', '2019-12-10'),
(30, 8, 25, 'karen', 'localhost', '2019-12-10'),
(31, 8, 26, 'karen', 'localhost', '2019-12-10'),
(32, 9, 11, 'karen', 'localhost', '2019-12-10'),
(33, 9, 23, 'karen', 'localhost', '2019-12-10'),
(34, 10, 5, 'karen', 'localhost', '2019-12-10'),
(35, 10, 25, 'karen', 'localhost', '2019-12-10'),
(36, 10, 22, 'karen', 'localhost', '2019-12-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `route_stop_transport`
--

CREATE TABLE `route_stop_transport` (
  `id_route_stop_transport` int(11) NOT NULL,
  `transport_id_transport` int(11) NOT NULL,
  `route_id_route` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stop`
--

CREATE TABLE `stop` (
  `id_stop` int(11) NOT NULL,
  `stop_status` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `stop`
--

INSERT INTO `stop` (`id_stop`, `stop_status`, `tx_host`, `tx_user`, `tx_date`, `latitude`, `longitude`, `description`) VALUES
(1, 1, 'localhost', 'karen', '2019-12-10', -16.49, -68.2091, 'P Teleferico Río Seco'),
(2, 1, 'localhost', 'karen', '2019-12-10', -16.4895, -68.1929, 'P Teleferico Plaza Upea'),
(3, 1, 'localhost', 'karen', '2019-12-10', -16.4921, -68.1831, 'P Teleferico Plaza La Paz'),
(4, 1, 'localhost', 'karen', '2019-12-10', -16.4948, -68.1735, 'P Teleferico Plaza 16 de julio'),
(5, 1, 'localhost', 'karen', '2019-12-10', -16.4978, -68.1648, 'P Jacha Qhathu'),
(6, 1, 'localhost', 'karen', '2019-12-10', -16.4981, -68.1529, 'P Teleferico Cementerio'),
(7, 1, 'localhost', 'karen', '2019-12-10', -16.4912, -68.1444, 'P Teleferico Estacion Central'),
(8, 1, 'localhost', 'karen', '2019-12-10', -16.4904, -68.1369, 'P Teleferico Armentia'),
(9, 1, 'localhost', 'karen', '2019-12-10', -16.4867, -68.1313, 'P Teleferico Periferica'),
(10, 1, 'localhost', 'karen', '2019-12-10', -16.4846, -68.1218, 'P Heroes de la Revolucion'),
(11, 1, 'localhost', 'karen', '2019-12-10', -16.4937, -68.1213, 'P Teleferico Monumento a Busch'),
(12, 1, 'localhost', 'karen', '2019-12-10', -16.5041, -68.1209, 'P Teleferico Plaza Triangular'),
(13, 1, 'localhost', 'karen', '2019-12-10', -16.5006, -68.1323, 'P Teleferico Prado'),
(14, 1, 'localhost', 'karen', '2019-12-10', -16.5041, -68.126, 'P Teleferico Teatro al Aire Libre'),
(15, 1, 'localhost', 'karen', '2019-12-10', -16.5107, -68.1207, 'P Teleferico Av. del Poeta'),
(16, 1, 'localhost', 'karen', '2019-12-10', -16.5193, -68.1162, 'P Teleferico del Libertador'),
(17, 1, 'localhost', 'karen', '2019-12-10', -16.5215, -68.1104, 'P Teleferico Alto Obrajes'),
(18, 1, 'localhost', 'karen', '2019-12-10', -16.5267, -68.1014, 'P Teleferico Obrajes'),
(19, 1, 'localhost', 'karen', '2019-12-10', -16.5381, -68.0874, 'P Teleferico Irpavi'),
(20, 1, 'localhost', 'karen', '2019-12-10', -16.5147, -68.1305, 'P Teleferico Sopocachi'),
(21, 1, 'localhost', 'karen', '2019-12-10', -16.5157, -68.1458, 'P Teleferico Buenos Aires'),
(22, 1, 'localhost', 'karen', '2019-12-10', -16.5186, -68.1503, 'P Teleferico Mirador'),
(23, 1, 'localhost', 'karen', '2019-12-10', -16.4978, -68.115, 'P Teleferico Las Villas'),
(24, 1, 'localhost', 'karen', '2019-12-10', -16.5227, -68.1693, 'P Teleferico Av. 6 de marzo'),
(25, 1, 'localhost', 'karen', '2019-12-10', -16.5123, -68.1537, 'P Teleferico Faro Murillo'),
(26, 1, 'localhost', 'karen', '2019-12-10', -16.5, -68.1352, 'P Teleferico San Jose');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transport`
--

CREATE TABLE `transport` (
  `id_transport` int(11) NOT NULL,
  `transport_status` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `description` varchar(200) NOT NULL,
  `transport_info_id_transport_info` int(11) NOT NULL,
  `route_image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `transport`
--

INSERT INTO `transport` (`id_transport`, `transport_status`, `tx_host`, `tx_user`, `tx_date`, `description`, `transport_info_id_transport_info`, `route_image`) VALUES
(0, 1, 'localhost', 'fer', '2019-11-15', 'Inca Llojeta', 0, 'https://www.google.com/maps/d/embed?mid=1JsQc8sA0Nx-DOvIPTBzgwu4ZvcIFxatX'),
(1, 1, 'localhost', 'fer', '2019-11-15', 'Villa Salome', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutavillasalome.pdf'),
(2, 1, 'localhost', 'fer', '2019-11-15', 'Chasquipampa', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutachasquipampa.pdf'),
(3, 1, 'localhost', 'fer', '2019-11-15', 'Caja Ferroviaria', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/%20CAJA%20FERROVIARIA-02-02-02.jpg'),
(4, 1, 'localhost', 'fer', '2019-11-15', 'Integradora', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/Mapa%20Final%20Integradora%20Web-01.png'),
(5, 1, 'localhost', 'fer', '2019-11-15', 'Irpavi II', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/rutas/IRPAVI%20MAPA%20SOLO-02.pdf'),
(6, 1, 'localhost', 'fer', '2019-11-15', 'Achumani', 0, 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/Mapa%20Oficial%20Achumani.pdf'),
(7, 1, 'localhost', 'fer', '2019-11-15', 'Linea Naranja', 1, 'https://www.google.com/maps/d/embed?mid=1XwP_S5xdWhd_CwpjcBMdues8D0GzwslR'),
(8, 1, 'localhost', 'fer', '2019-11-15', 'Linea Amarilla', 1, 'https://www.google.com/maps/d/embed?mid=1nWRd7tA0oB_kmUl3YCWrtqSiPlidxBc1'),
(9, 1, 'localhost', 'fer', '2019-11-15', 'Linea Azul', 1, 'https://www.google.com/maps/d/embed?mid=17P2EM-3_sdyOFiEB3otH2I-n9-Vg9q4C'),
(10, 1, 'localhost', 'fer', '2019-11-15', 'Linea Blanca', 1, 'https://www.google.com/maps/d/embed?mid=1j953ojotqhF6o6zIeVKeEuTJe6jhiWue'),
(11, 1, 'localhost', 'fer', '2019-11-15', 'Linea Cafe', 1, 'https://www.google.com/maps/d/embed?mid=1smy2gc-wzjMlOyP_Vd4viqVo6sHudq1E'),
(12, 1, 'localhost', 'fer', '2019-11-15', 'Linea Celeste', 1, 'https://www.google.com/maps/d/embed?mid=1xlLZ1l2OykTpaeyluBflkR12LnnNhw43'),
(13, 1, 'localhost', 'fer', '2019-11-15', 'Linea Morada', 1, 'https://www.google.com/maps/d/embed?mid=1ylisSj1I8nVr1hTENTd9KQoN8tGpPp0t'),
(14, 1, 'localhost', 'fer', '2019-11-15', 'Linea Plateada', 1, 'https://www.google.com/maps/d/embed?mid=1pc8dC5aG9bFKQyk238RAUAmIKCdub-Op'),
(15, 1, 'localhost', 'fer', '2019-11-15', 'Linea Roja', 1, 'https://www.google.com/maps/d/embed?mid=1osz2LmudoqBe784v_8H53NbgXEkRSOPp'),
(16, 1, 'localhost', 'fer', '2019-11-15', 'Linea Verde', 1, 'https://www.google.com/maps/d/embed?mid=16zO-9hCUuupQotNH0PLxqaCHkTyPy53Y');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transport_info`
--

CREATE TABLE `transport_info` (
  `id_transport_info` int(11) NOT NULL,
  `tx_hosts` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `type` int(11) NOT NULL,
  `info_description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `transport_info`
--

INSERT INTO `transport_info` (`id_transport_info`, `tx_hosts`, `tx_user`, `tx_date`, `type`, `info_description`) VALUES
(0, 'loclahost', 'fer', '2019-11-15', 1, 'Puma Katari'),
(1, 'loclahost', 'fer', '2019-11-15', 1, 'Teleferico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `id_user_bot` int(11) NOT NULL,
  `id_user_type` int(11) NOT NULL,
  `u_status` int(11) NOT NULL,
  `tx_host` varchar(200) NOT NULL,
  `tx_user` varchar(200) NOT NULL,
  `tx_date` date NOT NULL,
  `user_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id_user`, `id_user_bot`, `id_user_type`, `u_status`, `tx_host`, `tx_user`, `tx_date`, `user_name`) VALUES
(1, 710762348, 1, 1, 'localhost', 'fer', '2019-12-10', 'Fer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_chat`
--

CREATE TABLE `user_chat` (
  `id_user_chat` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `in_message` varchar(400) DEFAULT NULL,
  `out_message` varchar(400) DEFAULT NULL,
  `msg_date` date NOT NULL,
  `tx_user` varchar(50) NOT NULL,
  `tx_host` varchar(100) NOT NULL,
  `tx_date` date NOT NULL,
  `point_conversation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_chat`
--

INSERT INTO `user_chat` (`id_user_chat`, `id_user`, `in_message`, `out_message`, `msg_date`, `tx_user`, `tx_host`, `tx_date`, `point_conversation`) VALUES
(1, 1, '/start', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(2, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(3, 1, 'Teleferico', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(4, 1, 'Linea Amarilla', 'https://i.ibb.co/DMVmW6Z/Amarilla.jpg', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(5, 1, 'Linea Amarilla', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(6, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(7, 1, 'Teleferico', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(8, 1, 'Teleferico', 'Teleferico', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(9, 1, 'Linea Azul', 'https://www.google.com/maps/d/embed?mid=17P2EM-3_sdyOFiEB3otH2I-n9-Vg9q4C', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(10, 1, 'hol', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(11, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(12, 1, 'Teleferico', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(13, 1, 'Linea Blanca', 'https://www.google.com/maps/d/embed?mid=1j953ojotqhF6o6zIeVKeEuTJe6jhiWue', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(14, 1, 'jkjkh', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(15, 1, 'Ayuda', 'Escogiste ayuda en que podemos ayudar', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(16, 1, 'Pregunta 3', 'Ayuda', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(17, 1, 'Pregunta 4', 'Pregunta 3', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(18, 1, 'Pregunta 4', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(19, 1, 'Ayuda', 'Escogiste ayuda en que podemos ayudar', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(20, 1, 'Pregunta 3', 'Respuesta 3', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(21, 1, 'd', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(22, 1, 'Ayuda', 'Escogiste ayuda en que podemos ayudar', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(23, 1, 'Otra pregunta', 'Comunicate con nosotros! Ingresa al grupo \r\n https://t.me/joinchat/Kl1fbBXDJVwq8uwJ6rcNNA', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(24, 1, 'fg', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(25, 1, 'Buscar movilidad a mi destino', 'Elige una opcion+\n', '2019-12-10', '710762348', '710762348', '2019-12-10', 5),
(26, 1, 'Puma Katari', 'Envia tu ubicacion', '2019-12-10', '710762348', '710762348', '2019-12-10', 6),
(27, 1, NULL, 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(28, 1, NULL, 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(29, 1, 'Buscar movilidad a mi destino', 'Elige una opcion+\n', '2019-12-10', '710762348', '710762348', '2019-12-10', 5),
(30, 1, 'Teleferico', 'Envia tu ubicacion', '2019-12-10', '710762348', '710762348', '2019-12-10', 6),
(31, 1, NULL, 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(32, 1, 'Ayuda', 'Escogiste ayuda en que podemos ayudar', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(33, 1, 'Otra pregunta', 'Comunicate con nosotros! Ingresa al grupo \r\n https://t.me/joinchat/Kl1fbBXDJVwq8uwJ6rcNNA', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(34, 1, 'K', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(35, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(36, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(37, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(38, 1, 'Villa Salome', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(39, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(40, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(41, 1, 'Villa Salome', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutavillasalome.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(42, 1, '\'', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(43, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(44, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(45, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(46, 1, 'Inca Llojeta', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(47, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(48, 1, 'Teleferico', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(49, 1, 'Linea Amarilla', 'https://www.google.com/maps/d/embed?mid=1nWRd7tA0oB_kmUl3YCWrtqSiPlidxBc1', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(50, 1, 'Linea Amarilla', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(51, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(52, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(53, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(54, 1, 'Inca Llojeta', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(55, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(56, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(57, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(58, 1, 'Villa Salome', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(59, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(60, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(61, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(62, 1, 'Villa Salome', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(63, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(64, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(65, 1, 'Villa Salome', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutavillasalome.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(66, 1, 'Chasquipampa', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(67, 1, 'Buscar movilidad a mi destino', 'Elige una opcion+\n', '2019-12-10', '710762348', '710762348', '2019-12-10', 5),
(68, 1, 'Puma Katari', 'Envia tu ubicacion', '2019-12-10', '710762348', '710762348', '2019-12-10', 6),
(69, 1, 'KL', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(70, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(71, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(72, 1, 'Inca Llojeta', 'http://www.lapazbus.bo/fileman/Uploads/files/pdf/mapa_rutaincallojeta.pdf', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(73, 1, 'D', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(74, 1, 'Ayuda', 'Escogiste ayuda en que podemos ayudar', '2019-12-10', '710762348', '710762348', '2019-12-10', 9),
(75, 1, 'Otra pregunta', 'Comunicate con nosotros! Ingresa al grupo \r\n https://t.me/joinchat/Kl1fbBXDJVwq8uwJ6rcNNA', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(76, 1, 'SF', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(77, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(78, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(79, 1, 'Inca Llojeta', 'https://www.google.com/maps/d/emebed?mid=1JsQc8sA0Nx-DOvIPTBzgwu4ZvcIFxatX', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(80, 1, 'Inca Llojeta', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(81, 1, 'Buscar una línea específica', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 2),
(82, 1, 'Puma Katari', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 3),
(83, 1, 'Inca Llojeta', 'https://www.google.com/maps/d/embed?mid=1JsQc8sA0Nx-DOvIPTBzgwu4ZvcIFxatX', '2019-12-10', '710762348', '710762348', '2019-12-10', 0),
(84, 1, 'df', 'Elige una opcion', '2019-12-10', '710762348', '710762348', '2019-12-10', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_type`
--

CREATE TABLE `user_type` (
  `id_user_type` int(11) NOT NULL,
  `type` varchar(400) NOT NULL,
  `token` text DEFAULT NULL,
  `tx_user` varchar(50) NOT NULL,
  `tx_host` varchar(100) NOT NULL,
  `tx_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_type`
--

CREATE TABLE `favorite_routes` (
  `id_favorite_routes` int(11) NOT NULL,
  `id_route` int(11) NOT NULL,
  `Name` text DEFAULT NULL,
  `tx_user` varchar(50) NOT NULL,
  `tx_host` varchar(100) NOT NULL,
  `tx_date` date NOT NULL,
  primary key (`id_favorite_routes`),
  foreign key (`id_route`) references `route`(`id_route`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user_type` (`id_user_type`, `type`, `token`, `tx_user`, `tx_host`, `tx_date`) VALUES
(1, 'Administrador', '992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4', 'fer', 'localhost', '2019-11-22'),
(2, 'Usuario', NULL, 'fer', 'localhost', '2019-11-22');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `connection_routes`
--
ALTER TABLE `connection_routes`
  ADD PRIMARY KEY (`id_coroutes`),
  ADD KEY `route_a` (`route_a`),
  ADD KEY `route_b` (`route_b`),
  ADD KEY `type_connection` (`type_connection`);

--
-- Indices de la tabla `exception`
--
ALTER TABLE `exception`
  ADD PRIMARY KEY (`id_exception`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`);

--
-- Indices de la tabla `parada_taxi`
--
ALTER TABLE `parada_taxi`
  ADD PRIMARY KEY (`id_taxi`);

--
-- Indices de la tabla `ride_data`
--
ALTER TABLE `ride_data`
  ADD PRIMARY KEY (`id_ride`),
  ADD KEY `ride_data_route` (`route_id_route`),
  ADD KEY `ride_data_transport` (`transport_id_transport`),
  ADD KEY `ride_data_users` (`users_id_user`);

--
-- Indices de la tabla `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`id_route`),
  ADD KEY `route_stop` (`stop_finish`),
  ADD KEY `stop_start` (`stop_start`);

--
-- Indices de la tabla `route_stop`
--
ALTER TABLE `route_stop`
  ADD PRIMARY KEY (`id_route_stop`),
  ADD KEY `route_stop_route` (`route_id_route`),
  ADD KEY `route_stop_stop` (`stop_id_stop`);

--
-- Indices de la tabla `route_stop_transport`
--
ALTER TABLE `route_stop_transport`
  ADD PRIMARY KEY (`id_route_stop_transport`),
  ADD KEY `route_stop_transport_route` (`route_id_route`),
  ADD KEY `route_stop_transport_transport` (`transport_id_transport`);

--
-- Indices de la tabla `stop`
--
ALTER TABLE `stop`
  ADD PRIMARY KEY (`id_stop`);

--
-- Indices de la tabla `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id_transport`),
  ADD KEY `transport_transport_info` (`transport_info_id_transport_info`);

--
-- Indices de la tabla `transport_info`
--
ALTER TABLE `transport_info`
  ADD PRIMARY KEY (`id_transport_info`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `users_user_type` (`id_user_type`);

--
-- Indices de la tabla `user_chat`
--
ALTER TABLE `user_chat`
  ADD PRIMARY KEY (`id_user_chat`),
  ADD KEY `user_chat_users` (`id_user`);

--
-- Indices de la tabla `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id_user_type`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `connection_routes`
--
ALTER TABLE `connection_routes`
  MODIFY `id_coroutes` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `exception`
--
ALTER TABLE `exception`
  MODIFY `id_exception` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `parada_taxi`
--
ALTER TABLE `parada_taxi`
  MODIFY `id_taxi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ride_data`
--
ALTER TABLE `ride_data`
  MODIFY `id_ride` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `route`
--
ALTER TABLE `route`
  MODIFY `id_route` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `route_stop`
--
ALTER TABLE `route_stop`
  MODIFY `id_route_stop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `route_stop_transport`
--
ALTER TABLE `route_stop_transport`
  MODIFY `id_route_stop_transport` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `stop`
--
ALTER TABLE `stop`
  MODIFY `id_stop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `transport`
--
ALTER TABLE `transport`
  MODIFY `id_transport` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de la tabla `transport_info`
--
ALTER TABLE `transport_info`
  MODIFY `id_transport_info` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `user_chat`
--
ALTER TABLE `user_chat`
  MODIFY `id_user_chat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT de la tabla `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id_user_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `connection_routes`
--
ALTER TABLE `connection_routes`
  ADD CONSTRAINT `connection_routes_ibfk_1` FOREIGN KEY (`route_a`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `connection_routes_ibfk_2` FOREIGN KEY (`route_b`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `connection_routes_ibfk_3` FOREIGN KEY (`type_connection`) REFERENCES `transport_info` (`id_transport_info`);

--
-- Filtros para la tabla `ride_data`
--
ALTER TABLE `ride_data`
  ADD CONSTRAINT `ride_data_route` FOREIGN KEY (`route_id_route`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `ride_data_transport` FOREIGN KEY (`transport_id_transport`) REFERENCES `transport` (`id_transport`),
  ADD CONSTRAINT `ride_data_users` FOREIGN KEY (`users_id_user`) REFERENCES `users` (`id_user`);

--
-- Filtros para la tabla `route`
--
ALTER TABLE `route`
  ADD CONSTRAINT `route_stop` FOREIGN KEY (`stop_finish`) REFERENCES `stop` (`id_stop`),
  ADD CONSTRAINT `stop_start` FOREIGN KEY (`stop_start`) REFERENCES `stop` (`id_stop`);

--
-- Filtros para la tabla `route_stop`
--
ALTER TABLE `route_stop`
  ADD CONSTRAINT `route_stop_route` FOREIGN KEY (`route_id_route`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `route_stop_stop` FOREIGN KEY (`stop_id_stop`) REFERENCES `stop` (`id_stop`);

--
-- Filtros para la tabla `route_stop_transport`
--
ALTER TABLE `route_stop_transport`
  ADD CONSTRAINT `route_stop_transport_route` FOREIGN KEY (`route_id_route`) REFERENCES `route` (`id_route`),
  ADD CONSTRAINT `route_stop_transport_transport` FOREIGN KEY (`transport_id_transport`) REFERENCES `transport` (`id_transport`);

--
-- Filtros para la tabla `transport`
--

ALTER TABLE `transport`
  ADD foreign key (`transport_info_id_transport_info`) references `transport_info` (`id_transport_info`);
-- transport_transport_info
-- transport_info_id_transport_info
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_user_type` FOREIGN KEY (`id_user_type`) REFERENCES `user_type` (`id_user_type`);

--
-- Filtros para la tabla `user_chat`
--
ALTER TABLE `user_chat`
  ADD CONSTRAINT `user_chat_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);
COMMIT;