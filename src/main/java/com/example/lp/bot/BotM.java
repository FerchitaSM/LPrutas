package com.example.lp.bot;

import com.example.lp.bl.BotBl;
import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotMain.class);
    SendMessage message = new SendMessage();
    BotOpciones op;
    List<String> opciones;
    TransportBl transportBl;
    TransportInfoBl transportInfoBl;
    public static int conversacion=0;
    public static String mensaje="";

    @Autowired
    public BotM(TransportBl transportBl, TransportInfoBl transportInfoBl) {
        this.transportBl =transportBl;
        this.transportInfoBl=transportInfoBl;
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
               log.info("HOLAAAAAAAAAAAAAA");
               conversacion=1;
               break;
           case 1:
               if(update.getMessage().hasLocation()){
                   String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
                   log.info("LA UBICACION ESSSSSSSSSSSSSSSSSSSSS:     "+latitud);
                   log.info(longitud);
                   mensaje="Envia la ubicacion de a donde quieres llegar";
                   conversacion=2;
               }else{
                   conversacion=0;
                   break;
               }
               break;
           case 2:
               if(update.getMessage().hasLocation()){
                   String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
                   log.info("LA UBICACION DE LLEGADA ESSSSSSSSSSSSSSSSSSSSS:     "+latitud);
                   log.info(longitud);
                   mensaje="Grandioso ya tenemos la informacion";
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


