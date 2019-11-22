package com.example.lp.bot;

import com.example.lp.bl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BotMainLucho extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BootMain.class);
    SendMessage message = new SendMessage(); // mensaje

    BotOpciones op; // para invocar a la clase opciones del bot
    List<String> opciones; // lista de opciones del menu

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;
    StopBl stopBl;
    RouteBl routeBl;

    private static int point_conversation=0; //punto en el que se encuentra la conversacion
    boolean menu= true;
    BotBl botBl;
    @Autowired
    public BotMainLucho(BotBl customerBl) {
        this.botBl = customerBl;
        this.transportBl =transportBl;
        this.transportInfoBl=transportInfoBl;
        this.stopBl=stopBl;
        this.routeBl=routeBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId(); //id del ususario

        ReplyKeyboardMarkup respuesta_botones = (ReplyKeyboardMarkup) responderBotones(update); // para los botones
        String respuesta_texto=responderTexto(update); // parael texto

        message
                .setChatId(chat_id)
                .setText(respuesta_texto)
                .setReplyMarkup(respuesta_botones);

        if(opciones.size()==0) {
            ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
            message.setReplyMarkup(keyboardMarkupRemove);
            menu= false;
        } // para borrar el menu


        try {
            this.execute(message);
            log.info("mensaje enviado");
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }
    }
    private ReplyKeyboard responderBotones(Update update ) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        if(menu) {
            String call_data = update.getMessage().getText();
            op = new BotOpciones(call_data, transportBl, transportInfoBl);
            opciones = op.getRetornar();
            //lista con opciones
            for (int i = 0; i < opciones.size(); i++) {
                KeyboardRow row = new KeyboardRow();
                row.add(opciones.get(i));
                keyboard.add(row);
            }
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private String responderTexto(Update update) {
        String mensaje="Elige una opcion ";

        List<String> mostrar = op.getMostrar();
        if (mostrar.size() > 0)
            mensaje = mandar_url_imagen(mostrar.get(0));

        if(opciones.size()==0 && mostrar.size() == 0) {
            //mensaje = "Debes enviarme tu lugar de origen atravez de google maps";
            mensaje=respuesta(point_conversation, update);
        }
        return mensaje;
    }

    private String mandar_url_imagen(String ur) {
        String ret="";
        try {
            URL url = new URL(ur);
            ret = url + "\n";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ret;

    }
    public String respuesta(int conversation, Update update) {
        String mensaje="Envia tu ubicacion";
        List<Integer> list_origin=new ArrayList<>();//Lista de paradas cercanas al origen
        List<Integer> list_destination=new ArrayList<>();//Lista de paradas cercanas al destino

        switch (conversation) {
            case 0:
                point_conversation=1;
                break;
            case 1:
                if(update.getMessage().hasLocation()){
                    String u_origin=obteber_ubicacion(update);//Se obtiene la latitud y longitud del usuario
                    list_origin=llenar_lista(u_origin);//Obteniendo una lista con los lugares mas cercanos a mi ubicacion
                    mensaje="Envia la ubicacion a donde quieres llegar";
                    point_conversation=2;
                }else{
                    point_conversation=0;
                    break;
                }
                break;
            case 2:
                if(update.getMessage().hasLocation()){
                    String u_destination=obteber_ubicacion(update);//Obteniendo la ubicacion del lugar al que el usuario quiere llegar
                    list_destination=llenar_lista(u_destination);//Obteniendo los puntos mas cercanos a mi destino
                    mensaje=mandar_url_dibujo(list_origin, list_destination);
                    point_conversation=0;
                }else{
                    point_conversation=0;
                    break;
                }
                break;
        }
        return mensaje;
    }

    private String obteber_ubicacion (Update update) {
        String   ubicacion= "";
        String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
        String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
        ubicacion =latitude+","+longitude;
        return ubicacion;
    }

    private String mandar_url_dibujo(List<Integer> list_origin, List<Integer> list_destination  ) {
        int codigo=routeBl.findRoute(list_origin,list_destination);//Se envia la lista de puntos cercanos a la ubicacion del usuario y la lista de los puntos cercanos a su destino
        String ret="";
        String url= null;
        try {
            url = routeBl.drawMap(codigo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Devolviendo la url corta
        ret="Grandioso ya tenemos la informacion\nIngresa al siguiente link para ver el bus a tomar:\n";
        ret=ret+url;
        return ret;

    }

    private List<Integer> llenar_lista(String ubicacion ){
        List<Integer> retorno =new ArrayList<>();
        retorno=stopBl.findAllNearbyLocationStop(ubicacion);//Obteniendo los puntos mas cercanos a mi destino
        return retorno;
    }

    @Override
    public String getBotUsername() {
        return "Rutas_La_Paz_Bot";
    }
    @Override
    public String getBotToken() {
        return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chat Grupo 878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg
        // 1069385476:AAHAGvkWlH9uiNRUeHU380MTNUOrolQO2W0
        //Luis 992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4
        // creence su chat bot para que podamos correr en conjunto si

    }
}


