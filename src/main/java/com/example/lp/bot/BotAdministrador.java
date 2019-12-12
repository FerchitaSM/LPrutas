package com.example.lp.bot;

import com.example.lp.bl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class BotAdministrador extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotAdministrador.class);

    SendMessage messageUser,messageAdmi;

    String respuesta="";
    long chatIdUser;

    public BotAdministrador(){ }
    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendAnswer(Update update) {
        long chatIdAdmi = update.getMessage().getChatId();
        respuesta = update.getMessage().getText(); // mesaje del usuario

            messageUser = new SendMessage()
                    .setChatId(chatIdUser)
                    .setText(respuesta);

            messageAdmi = new SendMessage()
                .setChatId(chatIdAdmi)
                .setText("La respuesta fue enviada");

            try {
                execute(messageUser);
                execute(messageAdmi);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    public void sendQuestion(Update update) {
        chatIdUser = update.getMessage().getChatId();
        String question = update.getMessage().getText();

        messageAdmi = new SendMessage()
                .setChatId(BotInicializator.chatIdAdmi)
                .setText("Preguntan: "+question);
        try {
            execute(messageAdmi);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
        //return "PreguntasLaPaz_bot";

    }

    @Override
    public String getBotToken() {
        return  "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";
        // return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo

    }




}
