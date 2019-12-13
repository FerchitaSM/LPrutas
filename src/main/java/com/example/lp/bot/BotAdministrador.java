package com.example.lp.bot;

import com.example.lp.bl.*;
import com.example.lp.domain.UserChatEntity;
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
import java.sql.Date;

public class BotAdministrador extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BotAdministrador.class);

    SendMessage messageUser,messageAdmi;

    String respuesta="La respuesta a tu ultima pregunta es: ";

    private UsersBl usersBl;

    public BotAdministrador(UsersBl usersBl){
        this.usersBl = usersBl;
    }
    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendAnswer(Update update) {
        long chatIdAdmi = update.getMessage().getChatId();
        respuesta += update.getMessage().getText(); // mesaje del usuario
        long chatIdUser =usersBl.chatIdUser();
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
        int chatIdUser = Integer.parseInt(String.valueOf(update.getMessage().getChatId()));
        int idAdmi=usersBl.findByIdUserBot((int) BotInicializator.chatIdAdmi).getIdUser();
        String question = update.getMessage().getText();
        Date sDate = getDate();
        UserChatEntity userChatEntity = new UserChatEntity();
        userChatEntity.setIdUser(idAdmi);
        userChatEntity.setInMessage(question);
        userChatEntity.setOutMessage("....");
        userChatEntity.setMsgDate(sDate);
        userChatEntity.setTxUser(update.getMessage().getFrom().getId().toString());
        userChatEntity.setTxHost(update.getMessage().getChatId().toString());
        userChatEntity.setTxDate(sDate);
        userChatEntity.setPointConversation(chatIdUser);

        log.info("...........................................................................");
        log.info(userChatEntity.toString());
        usersBl.continueWhitAdmi( userChatEntity);

        messageAdmi = new SendMessage()
                .setChatId(BotInicializator.chatIdAdmi)
                .setText("Preguntan: "+question);
        try {
            execute(messageAdmi);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private Date getDate() {
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
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
