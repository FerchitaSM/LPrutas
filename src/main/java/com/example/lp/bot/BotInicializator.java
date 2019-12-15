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
  //  public static  final long chatIdAdmi = 710762348;;
    public static  final int idAdmi = 1;;
    TransportBl transportBl;
    TaxiBl taxiBl;
    StopBl stopBl;
    RouteBl routeBl;
    UsersBl usersBl;
    ExceptionBl exceptionBl;
    HotelBl hotelBl;
    @Autowired

    //Inicializacion Fer
    public BotInicializator(TransportBl transportBl,TaxiBl taxiBl,StopBl stopBl, RouteBl routeBl, UsersBl usersBl, ExceptionBl exceptionBl, HotelBl hotelBl) {
        this.transportBl=transportBl;
        this.taxiBl=taxiBl;
        this.stopBl = stopBl;
        this.routeBl=routeBl;
        this.usersBl=usersBl;
        this.exceptionBl=exceptionBl;
        this.hotelBl=hotelBl;
    }
    @PostConstruct
   public void levantando_bot() {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
            telegramBotsApi.registerBot(new BotM(transportBl,stopBl,routeBl,usersBl,exceptionBl,hotelBl)); //Inicializacion Karen
          //   telegramBotsApi.registerBot(new BootMain(transportBl,taxiBl,stopBl,routeBl,usersBl,exceptionBl)); //Inicializacion Fer
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }

    }
}
