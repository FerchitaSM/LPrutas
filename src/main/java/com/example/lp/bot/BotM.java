package com.example.lp.bot;

import com.example.lp.bl.*;
import com.example.lp.domain.UsersEntity;
import com.example.lp.dto.UserChatDto;
import com.example.lp.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotM.class);
    //punto en el que se encuentra la conversacion
    //punto universal donde se encontraria la persona


    private static String universal_point="0";
    //mensaje a enviar al usuario
    private static String mensaje="HOLA";
    private static File file=null;


    StopBl stopBl;
    RouteBl routeBl;
    TransportBl transportBl;
    ExceptionBl exceptionBl;
    UsersBl usersBl;
    HotelBl hotelBl;
    BotAdministrador botAdministrador;


    @Autowired
    public BotM(TransportBl transportBl,StopBl stopBl, RouteBl routeBl, UsersBl usersBl,ExceptionBl exceptionBl,HotelBl hotelBl) {
        this.transportBl=transportBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;
        this.usersBl=usersBl;
        this.exceptionBl=exceptionBl;
        this.hotelBl= hotelBl;
        botAdministrador = new BotAdministrador(this.usersBl);
    }
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            long chat_id = update.getMessage().getChatId();
            if(chat_id== BotInicializator.chatIdAdmi){
                botAdministrador.sendAnswer(update);
            }
            System.out.println("LLEGO EL MENSAJE");
            usersBl.saveMessageAndUser( update);
            SendMessage message = new SendMessage()// Create a message object object
                    .setChatId(chat_id)
                    .setText(mensaje);

            getMessage(update);
           usersBl.changePointConversationChatMessage(update.getMessage().getChatId(), universal_point);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            try {
                keyboardMarkup=punto(update,keyboardMarkup);
            } catch (IOException e) {
                e.printStackTrace();
            }
          /*TODO hay un error cuando el mensaje es largo*/


            usersBl.changeResponseChatMessage(update.getMessage().getChatId(),mensaje);
            usersBl.changePointConversationChatMessage(update.getMessage().getChatId(), universal_point);


            message.setText(mensaje);
            if(keyboardMarkup!=null){
                message.setReplyMarkup(keyboardMarkup);
            }else{
                ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
                message.setReplyMarkup(keyboardMarkupRemove);
            }
            SendDocument document=new SendDocument()
                    .setChatId(chat_id)
                    .setCaption("MAP");

           try {
               execute(message); // Sending our message object to user
               if(file!=null){
                   document.setDocument(file);
                   execute(document);
                   file=null;
               }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
        }

    public ReplyKeyboardMarkup punto(Update update,ReplyKeyboardMarkup keyboardMarkup) throws IOException {
       //String conversacion = usersBl.lastPointConversation(update);
        String conversacion=universal_point;
       switch (conversacion){
           //ESTE ES EL NIVEL BASICO
           case "0":
               keyboardMarkup=inicio(keyboardMarkup);
               mensaje="Elige una opcion";
               break;
           //EN ESTE NIVEL SE ESCOGE UNA LINEA ESPECIFICA
           case "1":
               //Se muestra los tipos de transporte que hay
               mensaje="Elige una opcion";
               keyboardMarkup=transportBl.DescriptiontransportInfo(keyboardMarkup);
               universal_point="2";
               break;
           case "2":
               //Se muestra movilidades de dicho transporte
               mensaje="Elige una opcion";
               keyboardMarkup=transportBl.findAllDescriptiontransport(keyboardMarkup,update);
               universal_point="3";
               break;
           case "3":
               //Se muestra la ruta de la movilidad
               mensaje="Elige una opcion\n";
               mensaje= transportBl.findURLTransportByName2(update); //routeBl.drawMapByDescription(update.getMessage().getText());
               universal_point="0";
               break;

            //EN ESTE NIVEL SE BUSCA LINEAS A MI RUTA
           case "4":
               //Se muestra los tipos de transporte que hay
               //String mas=routeBl.get_transport(update);
               mensaje="Elige una opcion+\n";
               //mensaje=mensaje+mas;
               keyboardMarkup=transportBl.DescriptiontransportInfo(keyboardMarkup);
               universal_point="5";
               break;
           case "5":
               //Se pide la ubicacion
               keyboardMarkup=null;
               routeBl.get_transport(update);
               mensaje="Envia tu ubicacion";
               universal_point="6";
               break;
           case "6":
               //Se obtiene datos de paradas cercas a mi
               keyboardMarkup=null;
               mensaje=routeBl.route_one(update,mensaje);
               universal_point="7";
               break;
           case "7":
               //Se obtiene datos de paradas cercanas a mi destino y la ruta
               keyboardMarkup=null;
               mensaje=routeBl.route_two(update,mensaje);
               file=routeBl.return_file();
               universal_point="0";
               break;
           //EN ESTE NIVEL SE ESCOGIO VER LAS EXCEPCIONES
           case "8":
               keyboardMarkup=exceptionBl.findAllQuestionMessage(keyboardMarkup,update);
               mensaje="Escogiste ayuda en que podemos ayudar";
               universal_point="9";
               break;
           case "9":
               //Obtenemos la pregunta a la respuesta previa
               keyboardMarkup=null;
               mensaje=exceptionBl.findAnswerMessageByQuestionMessage(update);
               universal_point=exceptionBl.universalPoint(mensaje);
               break;
           case "10":
               //Si la pregunta fuera "Otra pregunta" ira aca
               keyboardMarkup=null;
               mensaje=exceptionBl.sendQuestionAdmi(update);
               universal_point="0";
               break;



       }

        return keyboardMarkup;
    }


    public void getMessage (Update update){
       if(update.getMessage().hasText()==true){
           String message=update.getMessage().getText();
           switch (message){
               case "Buscar una línea específica":
                   universal_point="1";
                   break;
               case "Buscar movilidad a mi destino":
                   universal_point="4";
                   break;
               case "Ayuda":
                   universal_point="8";
                   break;
               default:
                   if(universal_point=="2" || universal_point=="3" || universal_point=="5" || universal_point=="9"|| universal_point=="10" ){
                       log.info("EL MENSAJE ES ADMITIDO");
                   }else{
                       universal_point="0";
                   }
           }
       }else{
           if(update.getMessage().hasLocation()==true){
               if((update.getMessage().hasLocation()) && (universal_point=="6" || universal_point=="7")){
                   //  || universal_point=="14")){
                   log.info("EL MENSAJE ES ADMITIDO");
               }else{
                   universal_point="0";//usersBl.changePointConversationChatMessage(update.getMessage().getChatId(),universal_point);
               }
           }
       }
    }

    public ReplyKeyboardMarkup inicio(ReplyKeyboardMarkup keyboardMarkup){
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Buscar una línea específica");
        keyboard.add(row);  //primera linea
        row = new KeyboardRow();// Creando otra linea
        row.add("Buscar movilidad a mi destino"); // segunda linea
        keyboard.add(row);// Adicionando la segunda linea
        row = new KeyboardRow();
        row.add("Ayuda");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    @Override
    public String getBotUsername() {
        return "PreguntasLaPaz_bot";

    }

    @Override
    public String getBotToken() {
        return "1009052032:AAGzTMnE24Q4Nc7TJTmSsXdv2XSp-auMFHc";//chatbot karen
       // return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
       // return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chatbot Luis
    }




}



/*
           //EN ESTE NIVEL SE MUESTRA COMO LLEGAR A CIERTOS DESTINOS EN ESPECIFICO (HOTELES)
           case "10":
               //Se muestra los hoteles que hay
               mensaje="Elige una opcion";
               keyboardMarkup= hotelBl.findAllName(keyboardMarkup);
               universal_point="11";
               break;
           case "11":
               //Se muestra la informacion del hotel
               mensaje= hotelBl.findAllInformationHotel(update);
               keyboardMarkup= hotelBl.GoHotel(keyboardMarkup, update); //si no
               routeBl.data_hotel(hotelBl.findLatitude(update),hotelBl.findLongitude(update));
               universal_point="12"; // depnde del si o no
               break;

           case "12":
               //Se muestra los tipos de transporte que hay
               mensaje="Elige una opcion+\n";
               //mensaje=mensaje+mas;
               keyboardMarkup=transportBl.DescriptiontransportInfo(keyboardMarkup);
               universal_point="13";
               break;
           case "13":
               //Se pide la ubicacion
               keyboardMarkup=null;
               routeBl.get_transport(update);
               mensaje="Envia la ubicacion de partida";
               universal_point="14";
               break;
           case "14":
               //Se obtiene datos de paradas cercas a mi
               keyboardMarkup=null;
               mensaje=routeBl.route_hotel(update,mensaje);
               universal_point="0";
               break;

 */


