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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BootMain extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BootMain.class);
    SendMessage message = new SendMessage(); // mensaje

    BotOpciones op; // para invocar a la clase opciones del bot
    List<String> opciones; // lista de opciones del menu
    TaxiBl taxiBl;
    ExceptionBl exceptionBl;
    UsersBl usersBl;
    TransportBl transportBl;
    StopBl stopBl;
    RouteBl routeBl;


    UserChatDto userChatDto; //para la relacion de los puntos esto de abajo
    private boolean request_contact;
    private static int point_conversation=0; //punto en el que se encuentra la conversacion
    boolean menu= true;


    @Autowired
    public BootMain(TransportBl transportBl,TaxiBl taxiBl,StopBl stopBl, RouteBl routeBl, UsersBl usersBl,ExceptionBl exceptionBl) {

        this.transportBl=transportBl;
        this.taxiBl=taxiBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;
        this.usersBl=usersBl;
        this.exceptionBl=exceptionBl;
    }
    public String requestContact(boolean requestContact) {
        request_contact = requestContact;
        String contact = Boolean.toString(requestContact);
        return contact;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId(); //id del ususario
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        UserDto userDto = saveMessageAndUser( update);
        ReplyKeyboardMarkup respuesta_botones = (ReplyKeyboardMarkup) responderBotones(userDto, update); // para los botones
        String respuesta_texto=responderTexto(update); // parael texto

        message
           .setChatId(chat_id)
           .setText(respuesta_texto)
           .setReplyMarkup(respuesta_botones);

        if(opciones.size()==0) {
            ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
            message.setReplyMarkup(keyboardMarkupRemove);
        } // para borrar el menu
        if(op.getMostrar()==null)
            menu= false;
        else
            menu= true;

        try {
            this.execute(message);
            log.info("mensaje enviado");
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }

    }

    private UserDto saveMessageAndUser(Update update) {
        String ret="";
        UsersEntity usersEntity= null;
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        if(!usersBl.existingUser(chat_id)){
            usersEntity =  usersBl.registerUser(update.getMessage().getFrom());
            ret= "Eres nuevo";
        } else {
            usersEntity =  usersBl.findByIdUserBot(update.getMessage().getFrom().getId());
        }
        List<String> chatResponse= new ArrayList<>();
        userChatDto = usersBl.continueWhitUser(update);
        UserDto userDto = new UserDto(usersEntity);
        return userDto;
    }

    private ReplyKeyboard responderBotones(UserDto userDto ,Update update ) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        if(menu) {
            String mensaje_entrada = userChatDto.getInMessage();
            op = new BotOpciones(mensaje_entrada, transportBl,taxiBl,exceptionBl,usersBl,userDto);
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
        String mensaje="Selecciona una opción";

        String mostrar = op.getMostrar();
        if (!mostrar.equals(""))
        {
            switch (mostrar) {
                case "Karen":
                    mensaje=respuesta(point_conversation, update);
                    break;
                case "Escriba lo siguiente: Nombre de la tabla / Atributo a cambiar / Nuevo dato":
                    mensaje= "Escriba lo siguiente: Nombre de la tabla / Atributo a cambiar / Nuevo dato";
                    break;
                default:
                    String token =usersBl.getTokenAdministrador();
                    if(mostrar.equals(token)) {
                        mensaje= cambioTipoUsuario(token);
                        break;
                    }else{
                        mensaje = mandar_url_imagen(mostrar);
                        break;}
            }


        }

        return mensaje;
    }

    private String cambioTipoUsuario(String token) {
        String ret = "";
        UsersEntity usersEntity =usersBl.findByIdUser(userChatDto.getIdUser());
        ret= usersBl.changeUserTypeToAdministrator(usersEntity,token);
        return ret;
    }


    private String mandar_url_imagen(String ur) {
        String ret="";
        try {
            URL url = new URL(ur);
            ret = url + "\n";
        } catch (MalformedURLException e) {
            ret = ur;
            e.printStackTrace();
        }
        return ret;

    }


    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
        //return "PreguntasLaPaz_bot";

    }

    @Override
    public String getBotToken() {
        //return "1009052032:AAGzTMnE24Q4Nc7TJTmSsXdv2XSp-auMFHc";//chatbot karen
        // return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        return  "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";
        // return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chatbot Luis
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
                    list_origin=llenar_lista(obtener_latitud(update),obtener_longitud(update) );//Obteniendo los puntos mas cercanos a mi destino
                    mensaje="Envia la ubicacion a donde quieres llegar";
                    point_conversation=2;
                }else{
                    point_conversation=0;
                    break;
                }
                break;
            case 2:

        }
        return mensaje;
    }

    private String obtener_latitud (Update update) {
        String latitude=String.valueOf(update.getMessage().getLocation().getLatitude());
        return latitude;
    }
    private String obtener_longitud (Update update) {
        String longitude=String.valueOf(update.getMessage().getLocation().getLongitude());
        return longitude;
    }



    private List<Integer> llenar_lista(String latitude, String longitude ){
        List<Integer> retorno =new ArrayList<>();
        try {
            retorno=stopBl.findAllNearbyLocationStop(latitude+","+longitude,15);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }


}

