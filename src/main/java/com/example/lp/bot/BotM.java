package com.example.lp.bot;

import com.example.lp.bl.RouteBl;
import com.example.lp.bl.StopBl;
import com.example.lp.dao.RouteStopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotM.class);
    List<Integer> list_origin=new ArrayList<>();
    List<Integer> list_destination=new ArrayList<>();
    private static int conversacion=0;
    private static String mensaje="";
    private static String u_origin="";
    private static String u_destination="";
    StopBl stopBl;
    RouteBl routeBl;
    RouteStopRepository routeStopRepository;

    @Autowired
    public BotM(StopBl stopBl,RouteBl routeBl,RouteStopRepository routeStopRepository) {
        this.stopBl=stopBl;
        this.routeBl=routeBl;
        this.routeStopRepository=routeStopRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            SendMessage message=new SendMessage();
            long chat_id = update.getMessage().getChatId();
            message.setChatId(chat_id);
            respuesta(conversacion,update);
            message.setText(mensaje);
            try {
                log.info("mensaje enviado");
                execute(message);
            } catch (TelegramApiException e) {
                log.info("error");
                e.printStackTrace();}
        }
        }


   // }
   public void respuesta(int conversation, Update update) {
       switch (conversation) {
           case 0:
               mensaje="Envia tu ubicacion";
               conversacion=1;
               break;
           case 1:
               if(update.getMessage().hasLocation()){
                   mensaje="";
                   String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
                   u_origin=latitude+","+longitude;
                   list_origin=stopBl.findAllNearbyLocationStop(latitude+","+longitude);
                   mensaje="Envia la ubicacion a donde quieres llegar";
                   conversacion=2;
               }else{
                   conversacion=0;
                   break;
               }
               break;
           case 2:
               if(update.getMessage().hasLocation()){
                   String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
                   u_destination=latitude+","+longitude;
                   list_destination=stopBl.findAllNearbyLocationStop(latitude+","+longitude);
                   int codigo=routeBl.findRoute(list_origin,list_destination);
                   log.info(":3"+codigo);
                   String url= null;
                   try {
                       url = routeBl.drawMap(codigo);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   mensaje="Grandioso ya tenemos la informacion\nIngresa al siguiente link para ver el bus a tomar:\n";
                   mensaje=mensaje+url;
                   conversacion=0;


               }else{
                   conversacion=0;
                   break;
               }
               break;
       }
    }
    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
        //return "Rutas_La_Paz_Bot";
    }

    @Override
    public String getBotToken() {
        return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda
        //return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chat Grupo
        //992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4
        // creence su chat bot para que podamos correr en conjunto si

    }
}


