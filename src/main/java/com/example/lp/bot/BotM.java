package com.example.lp.bot;

import com.example.lp.bl.*;
import com.example.lp.domain.UsersEntity;
import com.example.lp.dto.UserChatDto;
import com.example.lp.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotM.class);
    //punto en el que se encuentra la conversacion
    //punto universal donde se encontraria la persona

    private static String universal_point="0";
    //mensaje a enviar al usuario
    private static String mensaje="HOLA";

    StopBl stopBl;
    RouteBl routeBl;
    TransportBl transportBl;
    ExceptionBl exceptionBl;
    UsersBl usersBl;

    @Autowired
    public BotM(TransportBl transportBl,StopBl stopBl, RouteBl routeBl, UsersBl usersBl,ExceptionBl exceptionBl) {
        this.transportBl=transportBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;
        this.usersBl=usersBl;
        this.exceptionBl=exceptionBl;
    }
    @Override
    public void onUpdateReceived(Update update) {
        saveMessageAndUser( update);

        if(update.hasMessage()){
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage()// Create a message object object
                    .setChatId(chat_id)
                    .setText(mensaje);
            getMessage(update);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup=punto(universal_point,update,keyboardMarkup);
            message.setText(mensaje);
            if(keyboardMarkup!=null){
                message.setReplyMarkup(keyboardMarkup);
            }
           try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        }

   public ReplyKeyboardMarkup punto(String conversacion,Update update,ReplyKeyboardMarkup keyboardMarkup){
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
               mensaje=transportBl.findURLTransportByName2(update);
               universal_point="0";
               break;

            //EN ESTE NIVEL SE BUSCA LINEAS A MI RUTA
           case "4":
               //Se muestra los tipos de transporte que hay
               mensaje="Elige una opcion";
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
               universal_point="0";
               break;

           //EN ESTE NIVEL SE ESCOGIO VER LAS EXCEPCIONES
           case "8":
               keyboardMarkup=null;
               mensaje="Escogiste excepciones";
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
               case "Excepciones":
                   universal_point="8";
                   break;
               default:
                   if(universal_point=="2" || universal_point=="3" || universal_point=="5" ){
                       log.info("EL MENSAJE ES ADMITIDO");
                   }else{
                       universal_point="0";
                   }
           }
       }else{
           if(update.getMessage().hasLocation()==true){
               if((update.getMessage().hasLocation()) && (universal_point=="6" || universal_point=="7")){
                   log.info("EL MENSAJE ES ADMITIDO");
               }else{
                   universal_point="0";
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
        row.add("Excepciones");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    @Override
    public String getBotUsername() {
       // return "pruebaRLP_bot";
        return "Rutas_La_Paz_Bot";

    }

    @Override
    public String getBotToken() {
       // return "1009052032:AAGzTMnE24Q4Nc7TJTmSsXdv2XSp-auMFHc";//chatbot karen
        return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        // chatbot Fernanda 1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws
       // return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chatbot Luis
    }

    private void saveMessageAndUser(Update update) {
        UsersEntity usersEntity= null;
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        if(!usersBl.existingUser(chat_id)){
            usersEntity =  usersBl.registerUser(update.getMessage().getFrom());
        } else {
            usersEntity =  usersBl.findByIdUserBot(update.getMessage().getFrom().getId());
        }
        List<String> chatResponse= new ArrayList<>();
        UserChatDto userChatDto = usersBl.continueWhitUser(update, Integer.parseInt(universal_point));
      //  UserDto userDto = new UserDto(usersEntity);
    }


}


