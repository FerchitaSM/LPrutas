package com.example.lp;

import com.example.lp.bl.StopBl;
import com.example.lp.dao.*;
import com.example.lp.domain.ConnectionRoutesEntity;
import com.example.lp.domain.RouteStopEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

//import java.util.Date;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest

class RouteBlTests {

    @Test
    void testProcessUpdateExistingUser() {
        /*Respecto al Telegram*/
        // Simulando al Telegram
        User user = Mockito.mock(User.class);
        Mockito.doReturn(1234).when(user).getId();
        Mockito.doReturn("Juan").when(user).getFirstName();
        Mockito.doReturn("Perez").when(user).getLastName();

        Message message = Mockito.mock(Message.class);
        Mockito.doReturn(user).when(message).getFrom();
        Mockito.doReturn("Hola").when(message).getText();
        Mockito.doReturn(1234).when(message).getChatId();

        Location location = Mockito.mock(Location.class);
        Mockito.doReturn(-16.4890967).when(location).getLongitude();
        Mockito.doReturn(-68.1377988).when(location).getLatitude();
        /*----------------------------------------------------------------------------*/

        Update update = Mockito.mock(Update.class);
        Mockito.doReturn(message).when(update).getMessage();

        /*Se genera la lista de origen y la lista de destino*/
        List<Integer> list_origin=null;
        list_origin.add(1);
        List<Integer> list_destination=null;
        list_destination.add(7);

        //Simulando acceso a RouteRepository
        RouteRepository routeRepository=Mockito.mock(RouteRepository.class);

        /*se obtiene la ruta de los puntos cercanos*/
        /*REVISAR*/
        //Simulando acceso a la base de datos de RouteStopRepository
        RouteStopEntity routeStopEntity=Mockito.mock(RouteStopEntity.class);
        Mockito.doReturn(1).when(routeStopEntity.getStopIdStop()).equals(1);
        Mockito.doReturn(2).when(routeStopEntity.getStopIdStop()).equals(7);
        RouteStopRepository routeStopRepository=Mockito.mock(RouteStopRepository.class);
        Mockito.doReturn(routeStopEntity).when(routeStopRepository).findRoute(1);


        /*Simulando acceso a la base de datos ConnectionRoutesRepository*/
        ConnectionRoutesEntity connectionRoutesEntity=Mockito.mock(ConnectionRoutesEntity.class);
        Mockito.doReturn(1).when(connectionRoutesEntity).getRouteA();
        Mockito.doReturn(2).when(connectionRoutesEntity).getRouteA();
        // Mockito.doReturn(4).when(connectionRoutesEntity).getTypeConnection();
        ConnectionRoutesRepository connectionRoutesRepository=Mockito.mock(ConnectionRoutesRepository.class);
        Mockito.doReturn(connectionRoutesEntity).when(connectionRoutesRepository).findAllByTypeTransport(4);

        //Simulando
        StopRepository stopRepository;
        StopBl stopBl;
        TransportInfoRepository transportInfoRepository;



        /*
        // Simulando acceso a base de datos CpUserRepository
        CpUser cpUser = Mockito.mock(CpUser.class);
        Mockito.doReturn(1).when(cpUser).getUserId();
        Mockito.doReturn("1234").when(cpUser).getBotUserId();
        CpUserRepository cpUserRepository = Mockito.mock(CpUserRepository.class);
        Mockito.doReturn(cpUser).when(cpUserRepository).findByBotUserId("1234");

        // Simulando aceso a base de datos CpPersonRepository
        CpPersonRepository cpPersonRepository = Mockito.mock(CpPersonRepository.class);

        // Simulando aceso a base de datos CpPersonRepository
        CpChat cpChat = Mockito.mock(CpChat.class);
        Mockito.doReturn("4").when(cpChat).getOutMessage();
        CpChatRepository cpChatRepository = Mockito.mock(CpChatRepository.class);
        Mockito.doReturn(cpChat).when(cpChatRepository).findLastChatByUserId(1);
          /*se crea las supuestas listas a probar*/

        /*Aqui se genera o se llama al inicializador con sus respectivos valores de cp tal*/
        //PRUEBA UNITARIA
        /*RouteBl routeBl = new RouteBl();
        /*llama al processUpdate*/
       /* String return_message = routeBl.kml(list_origin,list_destination);
        assertNotNull(return_message);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), "5");*/

    }
}