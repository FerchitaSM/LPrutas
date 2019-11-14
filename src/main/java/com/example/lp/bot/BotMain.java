package com.example.lp.bot;

import com.example.lp.bl.BotBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class BotMain extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(BotMain.class);

    BotBl botBl;

    public BotMain(BotBl customerBl) {
        this.botBl = customerBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        update.getMessage().getFrom().getId();
        if (update.hasMessage() && update.getMessage().hasLocation()) {
           /* List<String> messages = botBl.processUpdate(update);
            for(String messageText: messages) {*/
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        .setText("Tengo tu ubicacion");
               String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
               String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
               logger.info("latitud"+latitud);
               logger.info("longitud"+longitud);
                try {
                    this.execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //}
        }
    }

    @Override
    public String getBotUsername() {
        return "Rutas_La_Paz_Bot";
    }
    @Override
    public String getBotToken() {
        return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        // creence su chat bot para que podamos correr en conjunto si

    }
}


