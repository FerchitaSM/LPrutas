package com.example.lp.bot;


import com.example.lp.bl.UsersBl;
import com.example.lp.domain.UsersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BootUsuarios extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(BootUsuarios.class);
    SendMessage message = new SendMessage(); // mensaje

    UsersBl usersBl;

    @Autowired
    public BootUsuarios( UsersBl usersBl) {
        this.usersBl=usersBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chat_id = update.getMessage().getChatId(); //id del ususario
        String respuesta =responder(update);
        message
                .setChatId(chat_id)
                .setText(respuesta);

        try {
            this.execute(message);
            log.info("mensaje enviado");
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }

    }

    private String responder(Update update) {
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        if(usersBl.existingUser(chat_id)){
            UsersEntity usersEntity = usersBl.findByIdUserBot(chat_id);
            List<String> chatResponse= new ArrayList<>();
            usersBl.continueWhitUser(update);
            return chatResponse.get(chatResponse.size()-1);
        }else {
            usersBl.registerUser(update.getMessage().getFrom());
            return "Eres nuevo";
        }
    }


    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
    }

    @Override
    public String getBotToken() {
        return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda
    }

}
