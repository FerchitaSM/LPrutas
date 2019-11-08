package com.example.lp.bot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInicializator  {
    private static final Logger log = LoggerFactory.getLogger(BotInicializator.class);

    public BotInicializator(){
    }

    @PostConstruct
   public void levantando_bot() {
       ApiContextInitializer.init();
       TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
           telegramBotsApi.registerBot(new BotMain());
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }
    }
}
