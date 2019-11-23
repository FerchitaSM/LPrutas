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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BotM  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotM.class);
    //Lista de paradas cercanas al origen
    List<Integer> list_origin=new ArrayList<>();
    //Lista de paradas cercanas al destino
    List<Integer> list_destination=new ArrayList<>();
    //punto en el que se encuentra la conversacion
    //punto universal donde se encontraria la persona
    private static String universal_point="0";
    //mensaje a enviar al usuario
    private static String mensaje="HOLA";
    private static String u_origin="";
    private static String u_destination="";
    private static int level_two=0;


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
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage()// Create a message object object
                    .setChatId(chat_id)
                    .setText(mensaje);
            procesandoMensaje(update);
            punto(universal_point,update);
           // ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Creando el teclado inicial (list of keyboard rows)
            /*List<KeyboardRow> keyboard_inicial = new ArrayList<>();
            keyboard_inicial=point(point_conversation,update,mensaje);
            keyboardMarkup.setKeyboard(keyboard_inicial);*/
            // Add it to the message
            //message.setReplyMarkup(keyboardMarkup);
            message.setText(mensaje);
           try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }



/*
            SendMessage message=new SendMessage();
            long chat_id = update.getMessage().getChatId();
            message.setChatId(chat_id);
            respuesta(point_conversation,update);
            message.setText(mensaje);
            try {
                execute(message);
                log.info("mensaje enviado");

            } catch (TelegramApiException e) {
                log.info("error");
                e.printStackTrace();}*/
        }
        }

   public void punto(String conversacion,Update update){
       switch (conversacion){
           case "0":
               mensaje="OPCIONES PARTE 1";
               break;
           case "1":
               mensaje="LIN";
               universal_point="0";
               break;
           case "2":
               mensaje="ESCOGISTE MOV";
               mensaje=mensaje+"Envia tu ubicacion";
               universal_point="3";
               break;
           case "3":
               mensaje="Envia tu destino";
               universal_point="0";
               break;
           case "4":
               universal_point="0";
               break;
           case "5":

               break;
       }
      // return punto;
   }
  /* public int numero(Update update){
       int numero=0;
       if()
       return numero;
   }*/
   public void procesandoMensaje(Update update){

       if(update.getMessage().hasText()==true){
           String m=update.getMessage().getText();
           switch (m){
               case "LIN":
                   universal_point="1";
                   break;
               case "MOV":
                   universal_point="2";
                   break;
               default:
                   if((update.getMessage().getText()).equals("ubi") && universal_point=="2" || universal_point=="3"){
                       log.info("EL MENSAJE ES ADMITIDO");
                   }else{
                       universal_point="0";
                   }
           }
       }
       if(update.getMessage().hasLocation()==true){
           if((update.getMessage().hasLocation()) && universal_point=="2" || universal_point=="3"){
               log.info("EL MENSAJE ES ADMITIDO");
           }else{
               universal_point="0";
           }
       }

   }



    public List<KeyboardRow> inicio(List<KeyboardRow> keyboard){
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
        return keyboard;
    }
    public List<KeyboardRow> nivel_two(List<KeyboardRow> keyboard){
        KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Buscar ");
        keyboard.add(row);  //primera linea
        row = new KeyboardRow();// Creando otra linea
        row.add("Buscar movilidado"); // segunda linea
        keyboard.add(row);// Adicionando la segunda linea
        row = new KeyboardRow();
        row.add("Excep");
        keyboard.add(row);
        return keyboard;
    }


   // }
   /*public void respuesta(int conversation, Update update) {
       switch (conversation) {
           case 0:
               mensaje="Envia tu ubicacion";
               point_conversation=1;
               break;
           case 1:
               if(update.getMessage().hasLocation()){
                   mensaje="";
                   //Se obtiene la latitud y longitud del usuario
                   String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
                   u_origin=latitude+","+longitude;
                   //Obteniendo una lista con los lugares mas cercanos a mi ubicacion
                   try {
                       list_origin=stopBl.findAllNearbyLocationStop(latitude+","+longitude);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   mensaje="Envia la ubicacion a donde quieres llegar";
                   point_conversation=2;
               }else{
                   point_conversation=0;
                   break;
               }
               break;
           case 2:
               if(update.getMessage().hasLocation()){
                   //Obteniendo la ubicacion del lugar al que el usuario quiere llegar
                   String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
                   String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
                   u_destination=latitude+","+longitude;
                   //Obteniendo los puntos mas cercanos a mi destino
                   try {
                       list_destination=stopBl.findAllNearbyLocationStop(latitude+","+longitude);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   //Se envia la lista de puntos cercanos a la ubicacion del usuario y la lista de los puntos cercanos a su destino
                   int codigo=routeBl.findRoute(list_origin,list_destination);
                   //Generando la url (dibujando el mapa que se enviara)
                   String url= null;
                   try {
                       url = routeBl.drawMap(codigo);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   //Devolviendo la url corta
                   mensaje="Grandioso ya tenemos la informacion\nIngresa al siguiente link para ver el bus a tomar:\n";
                   mensaje=mensaje+url;
                   point_conversation=0;
               }else{
                   point_conversation=0;
                   break;
               }
               break;
       }
    }*/
    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
    }

    @Override
    public String getBotToken() {
        return "1009052032:AAGzTMnE24Q4Nc7TJTmSsXdv2XSp-auMFHc";//chatbot karen
       // return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda 1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws

       // return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chatbot Luis
        // creence su chat bot para que podamos correr en conjunto si

    }
}


