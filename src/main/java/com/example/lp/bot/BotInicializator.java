package com.example.lp.bot;


import com.example.lp.bl.BotBl;
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
    BotBl   botBl;
   /* MovilidadBl movilidadBl;
    TipoMovilidadBl tipomovilidadBl;

    public BotInicializator(MovilidadBl movilidadBl) {
        this.movilidadBl = movilidadBl;
    }

    public BotInicializator(TipoMovilidadBl tipomovilidadBl) {
        this.tipomovilidadBl = tipomovilidadBl;
    }


    public BotInicializator(MovilidadBl movilidadBl, TipoMovilidadBl tipomovilidadBl) {
    }
*/
    public BotInicializator(){
        log.info("...............................................................................");
        //log.info(String.valueOf(movilidadBl));
    }

    @PostConstruct
   public void levantando_bot() {
       ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
           telegramBotsApi.registerBot(new BotMain(botBl));
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }

    }
}
