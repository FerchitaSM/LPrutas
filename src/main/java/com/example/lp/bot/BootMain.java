package com.example.lp.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BootMain   extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(BotMain.class);
    SendMessage message = new SendMessage();
    BotOpciones op;List<String> opciones;
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
        op= new BotOpciones(call_data);
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
        if(opciones.get(0).equals("Enviar mi ubicacion")) {
            if (update.hasMessage() && update.getMessage().hasLocation()) {
                mensaje = "Tengo tu ubicacion";
                String latitud = String.valueOf(update.getMessage().getLocation().getLatitude());
                String longitud = String.valueOf(update.getMessage().getLocation().getLongitude());
                mensaje += " latitud:" + latitud + " longitud: " + longitud;
            }
        }
        return mensaje;
    }

    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
    }

    @Override
    public String getBotToken() {
        return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda
        //return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        //Luis 992556865:AAF_LERRNZvwv8zYiDJ6r3XCnHU6ytjCWc4
        // creence su chat bot para que podamos correr en conjunto si

    }
}


