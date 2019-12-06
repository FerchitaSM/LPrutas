
CREATE TABLE  parada 
   (id_parada int NOT NULL auto_increment, 
	statuss int,
    text_user varchar(200),
    text_host varchar(200),
	Nombre_Parada VARCHAR(200),
    latitud float,
    longitud float,
    PRIMARY KEY (id_parada)
   ) ;
CREATE TABLE  Ruta
   (	id_ruta int NOT NULL, 
	   statuss int,
		text_user varchar(200),
		text_host varchar(200),
		id_origen int NOT NULL,
		id_dllegada int NOT NULL, 
		FOREIGN KEY (id_origen) REFERENCES  parada (id_parada),
		FOREIGN KEY (id_dllegada) REFERENCES  parada (id_parada),
		 PRIMARY KEY (id_ruta)
   );
CREATE TABLE  tipo_movilidad
   (	id_tipo_movilidad int NOT NULL, 
		statuss int,
		text_user varchar(200),
		text_host varchar(200),
		descripcion varchar(200),
		PRIMARY KEY (id_tipo_movilidad)
   ) ;
CREATE TABLE  Movilidad
   (	id_movilidad int NOT NULL,
		statuss int,
		text_user varchar(200),
		text_host varchar(200),
		id_tipo_movilidad int not null,  
		FOREIGN KEY (id_tipo_movilidad)REFERENCES  tipo_movilidad (id_tipo_movilidad),
		PRIMARY KEY (id_movilidad)
   ) ;

  CREATE TABLE  movilidad_ruta
   (	id_movilidad_ruta int NOT NULL,
		statuss int,
		text_user varchar(200),
		text_host varchar(200),
		id_movilidad int NOT NULL,
		id_ruta int NOT NULL,
		FOREIGN KEY (id_movilidad)REFERENCES  Movilidad (id_movilidad),
		FOREIGN KEY (id_ruta)REFERENCES  ruta (id_ruta),
		PRIMARY KEY (id_movilidad_ruta)	
   ) ;