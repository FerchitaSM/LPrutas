package com.example.lp;

import bot.BotInicializator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LPrutasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LPrutasApplication.class, args);
        BotInicializator botInicializator = new BotInicializator();

    }

}
