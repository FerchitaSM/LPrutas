package com.example.lp.bot;

import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BotMainBotones extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotMainBotones.class);
    SendMessage message = new SendMessage();
    BotOpciones op;
    List<String> opciones;

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;

    @Autowired
    public BotMainBotones(TransportBl transportBl, TransportInfoBl transportInfoBl) {
        this.transportBl =transportBl;
        this.transportInfoBl=transportInfoBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId ="";
       chatId = update.getMessage().getFrom().getId().toString();

        ReplyKeyboardMarkup  respuesta_botones = null;
        respuesta_botones= (ReplyKeyboardMarkup) responderBotones(update);
        String respuesta_texto=responderTexto();

            message
                    .setChatId(chatId)
                    .setText(respuesta_texto)
                    .setReplyMarkup(respuesta_botones);
        if(opciones.size()==0)
        {
            ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
            message.setReplyMarkup(keyboardMarkupRemove);
        }



        try {
            this.execute(message);
            log.info("mensaje enviado");
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }
    }


    private ReplyKeyboard responderBotones(Update update ) {

            String call_data = update.getMessage().getText();
            op = new BotOpciones(call_data, transportBl, transportInfoBl);
            opciones = op.getRetornar();
            //lista con opciones

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();
            for (int i = 0; i < opciones.size(); i++) {
                KeyboardRow row = new KeyboardRow();
                row.add(opciones.get(i));
                keyboard.add(row);
            }
            keyboardMarkup.setKeyboard(keyboard);
            return keyboardMarkup;

    }

    private String responderTexto() {
        String mensaje="Elige una opcion ";
        List<String> mostrar = op.getMostrar();
        if(opciones.size()==0 && mostrar.size() == 0) {
            mensaje = "Debes enviarme tu lugar de origen atravez de google maps";
        }
        if (mostrar.size() > 0)
            mensaje = mandar_url(mostrar.get(0));

        return mensaje;
    }



    private String mandar_url(String ur) {
        String ret="";
        try {
            URL url = new URL(ur);
            ret = url + "\n";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ret;

    }



    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
    }

    @Override
    public String getBotToken() {
        return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda
        //return "992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4";  // chat Grupo
        //992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4

    }
}

