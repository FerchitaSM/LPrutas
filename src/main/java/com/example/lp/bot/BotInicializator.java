package com.example.lp.bot;


import com.example.lp.bl.BotBl;
import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInicializator  {
    private static final Logger log = LoggerFactory.getLogger(BotInicializator.class);
    BotBl   botBl;
    TransportBl transportBl;
    TransportInfoBl transportInfoBl;
    @Autowired
    public BotInicializator(TransportBl transportBl,TransportInfoBl transportInfoBl)
    {
        this.transportBl=transportBl;
        this.transportInfoBl=transportInfoBl;
    }
    /*
    public BotInicializator(BotBl botBl) {
        this.botBl = botBl;
    }
*/
    @PostConstruct
   public void levantando_bot() {
       ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
           //telegramBotsApi.registerBot(new BotMain(botBl));
           telegramBotsApi.registerBot(new BootMain(transportBl,transportInfoBl));
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }

    }
}
