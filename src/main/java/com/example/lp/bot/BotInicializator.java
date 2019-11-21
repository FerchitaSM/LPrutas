package com.example.lp.bot;
import com.example.lp.bl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    StopBl stopBl;
    RouteBl routeBl;
    @Autowired
    /*
    //Inicializacion Fer
    public BotInicializator(TransportBl transportBl,TransportInfoBl transportInfoBl,StopBl stopBl, RouteBl routeBl) {
        this.transportBl=transportBl;
        this.transportInfoBl=transportInfoBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;

    }*/

    /*//Inicializ
    public BotInicializator(BotBl botBl) {
        this.botBl = botBl;
    }*/

    //Inicializacion Cavero
   /* public BotInicializator(BotBl botBl,StopBl stopBl,RouteBl routeBl) {
        this.botBl = botBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;
    }*/

    //Inicializacion Karen
    public BotInicializator(StopBl stopBl, RouteBl routeBl) {
        this.stopBl = stopBl;
        this.routeBl=routeBl;
    }
    @PostConstruct
   public void levantando_bot() {
       ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
           //telegramBotsApi.registerBot(new BotMain(botBl)); //Registro Usuario Nuevo Cavero
           telegramBotsApi.registerBot(new BotMain(botBl)); //Nuevo Lucho
           //telegramBotsApi.registerBot(new BotM(stopBl,routeBl)); //Inicializacion Karen
           //telegramBotsApi.registerBot(new BootMain(transportBl,transportInfoBl,stopBl,routeBl)); //Inicializacion Fer
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }

    }
}
