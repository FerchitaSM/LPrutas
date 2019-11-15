package com.example.lp.bot;

import com.example.lp.bl.BotBl;
import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
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

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

public class BootMain  extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotMain.class);
    SendMessage message = new SendMessage();
    BotOpciones op;
    List<String> opciones;

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;

    @Autowired
    public BootMain(TransportBl transportBl, TransportInfoBl transportInfoBl) {
        this.transportBl =transportBl;
        this.transportInfoBl=transportInfoBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId ="";
        if(update.hasCallbackQuery())
        {chatId = update.getCallbackQuery().getFrom().getId().toString();}
        else
        { chatId = update.getMessage().getFrom().getId().toString();}

        InlineKeyboardMarkup respuesta_botones = new InlineKeyboardMarkup();
        respuesta_botones= (InlineKeyboardMarkup) responderBotones(update);
        String respuesta_texto=responderTexto(update);

        message
                .setChatId(chatId)
                .setText(respuesta_texto)
                .setReplyMarkup(respuesta_botones);


        try {
            log.info("mensaje enviado");
            this.execute(message);
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }
    }


    private ReplyKeyboard responderBotones(Update update) {
        String call_data="s/d";
        if(update.hasCallbackQuery()) {
            call_data=update.getCallbackQuery().getData();
        }
        op= new BotOpciones(call_data,transportBl,transportInfoBl);
        opciones = op.getRetornar();
        //lista con opciones

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        for (int i=0; i<opciones.size();i++)
        {
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            for (int j=i;j<=i;j++)
            {
                rowInline.add(new InlineKeyboardButton().setText(opciones.get(j)).setCallbackData(opciones.get(j)));
            }
            rowsInline.add(rowInline);
        }
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    private String responderTexto(Update update) {
        String mensaje="Elige una opcion ";
        if(opciones.size()>0) {
            if (opciones.get(0).equals("Enviar mi ubicacion")) {
                mensaje = "Tengo tu ubicacion";
                String latitud = String.valueOf(update.getMessage().getLocation().getLatitude());
                String longitud = String.valueOf(update.getMessage().getLocation().getLongitude());
                mensaje += " latitud:" + latitud + " longitud: " + longitud;
            }
        }
        List<String> mostrar = op.getMostrar();
            if (mostrar.size() > 0) {
                try {
                    URL url = new URL(mostrar.get(0));
                    mensaje = url + "\n";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }


        return mensaje;
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



