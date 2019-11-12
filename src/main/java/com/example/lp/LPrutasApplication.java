package com.example.lp;

import com.example.lp.bot.BotInicializator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.lp.dao","com.example.lp.bot" })
public class LPrutasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LPrutasApplication.class, args);
    }

}
