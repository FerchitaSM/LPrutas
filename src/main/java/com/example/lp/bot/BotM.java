package com.example.lp.bot;

import com.example.lp.bl.RouteBl;
import com.example.lp.bl.StopBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotM.class);
    List<Integer> list_origin=new ArrayList<>();
    List<Integer> list_destination=new ArrayList<>();
    public static int conversacion=0;
    public static String mensaje="";
    StopBl stopBl;
    RouteBl routeBl;
    @Autowired
    public BotM(StopBl stopBl,RouteBl routeBl) {
        this.stopBl=stopBl;
        this.routeBl=routeBl;
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
   public void respuesta(int conversation, Update update){
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
                   list_destination=stopBl.findAllNearbyLocationStop(latitude+","+longitude);
                   mensaje="Grandioso ya tenemos la informacion";
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


