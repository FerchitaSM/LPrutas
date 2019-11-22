CREATE TABLE ride_data (
    id_ride int NOT NULL auto_increment,
    users_id_user int NOT NULL,
    transport_id_transport int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    route_id_route int NOT NULL,
    CONSTRAINT ride_data_pk PRIMARY KEY (id_ride)
);

-- Table: route
CREATE TABLE route (
    id_route int NOT NULL auto_increment,
    route_status int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    route_details varchar(200) NOT NULL,
    stop_start int NOT NULL,
    stop_finish int NOT NULL,
    CONSTRAINT route_pk PRIMARY KEY (id_route)
);

-- Table: route_stop
CREATE TABLE route_stop (
    id_route_stop int NOT NULL auto_increment,
    route_id_route int NOT NULL,
    stop_id_stop int NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT route_stop_pk PRIMARY KEY (id_route_stop)
);

-- Table: route_stop_transport
CREATE TABLE route_stop_transport (
    id_route_stop_transport int NOT NULL auto_increment,
    transport_id_transport int NOT NULL,
    route_id_route int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT route_stop_transport_pk PRIMARY KEY (id_route_stop_transport)
);

-- Table: stop
CREATE TABLE stop (
    id_stop int NOT NULL auto_increment,
    stop_status int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    latitude float NOT NULL,
    longitude float NOT NULL,
    description varchar(200) NOT NULL,
    CONSTRAINT stop_pk PRIMARY KEY (id_stop)
);

-- Table: transport
CREATE TABLE transport (
    id_transport int NOT NULL auto_increment,
    transport_status int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    description varchar(200) NOT NULL,
    transport_info_id_transport_info int NOT NULL,
    route_image text NOT NULL,
    CONSTRAINT transport_pk PRIMARY KEY (id_transport)
);

-- Table: transport_info
CREATE TABLE transport_info (
    id_transport_info int NOT NULL auto_increment,
    tx_hosts varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    type int NOT NULL,
    info_description varchar(200) NOT NULL,
    CONSTRAINT transport_info_pk PRIMARY KEY (id_transport_info)
);


-- Table: users
CREATE TABLE users (
    id_user int NOT NULL auto_increment,
    id_user_bot int NOT NULL,
    u_status int NOT NULL,
    tx_host varchar(200) NOT NULL,
    tx_user varchar(200) NOT NULL,
    tx_date date NOT NULL,
    user_name varchar(100) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id_user)
);

CREATE TABLE user_chat (
    id_user_chat int  NOT NULL,
    id_user int  NOT NULL,
    in_message varchar(400)  NULL,
    out_message varchar(400)  NULL,
    msg_date date  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(100)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT user_chat_pk PRIMARY KEY (id_user_chat)
);

-- foreign keys
-- Reference: ride_data_route (table: ride_data)
ALTER TABLE ride_data ADD CONSTRAINT ride_data_route FOREIGN KEY ride_data_route (route_id_route)
    REFERENCES route (id_route);

-- Reference: ride_data_transport (table: ride_data)
ALTER TABLE ride_data ADD CONSTRAINT ride_data_transport FOREIGN KEY ride_data_transport (transport_id_transport)
    REFERENCES transport (id_transport);

-- Reference: ride_data_users (table: ride_data)
ALTER TABLE ride_data ADD CONSTRAINT ride_data_users FOREIGN KEY ride_data_users (users_id_user)
    REFERENCES users (id_user);

-- Reference: route_stop (table: route)
ALTER TABLE route ADD CONSTRAINT route_stop FOREIGN KEY route_stop (stop_finish)
    REFERENCES stop (id_stop);

-- Reference: route_stop_route (table: route_stop)
ALTER TABLE route_stop ADD CONSTRAINT route_stop_route FOREIGN KEY route_stop_route (route_id_route)
    REFERENCES route (id_route);

-- Reference: route_stop_stop (table: route_stop)
ALTER TABLE route_stop ADD CONSTRAINT route_stop_stop FOREIGN KEY route_stop_stop (stop_id_stop)
    REFERENCES stop (id_stop);

-- Reference: route_stop_transport_route (table: route_stop_transport)
ALTER TABLE route_stop_transport ADD CONSTRAINT route_stop_transport_route FOREIGN KEY route_stop_transport_route (route_id_route)
    REFERENCES route (id_route);

-- Reference: route_stop_transport_transport (table: route_stop_transport)
ALTER TABLE route_stop_transport ADD CONSTRAINT route_stop_transport_transport FOREIGN KEY route_stop_transport_transport (transport_id_transport)
    REFERENCES transport (id_transport);

-- Reference: stop_start (table: route)
ALTER TABLE route ADD CONSTRAINT stop_start FOREIGN KEY stop_start (stop_start)
    REFERENCES stop (id_stop);

-- Reference: transport_transport_info (table: transport)
ALTER TABLE transport ADD CONSTRAINT transport_transport_info FOREIGN KEY transport_transport_info (transport_info_id_transport_info)
    REFERENCES transport_info (id_transport_info);



-- Reference: users_user_info (table: users)
ALTER TABLE user_chat ADD CONSTRAINT user_chat_users FOREIGN KEY user_chat_users (id_user)
    REFERENCES users (id_user);

-- End of file.

