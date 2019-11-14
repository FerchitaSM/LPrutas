package com.example.lp;

import com.example.lp.bot.BotInicializator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(scanBasePackages = { "com.example.lp.dao","com.example.lp.bot","com.example.lp.bl"})
//@EnableJpaRepositories(basePackages = {"com.example.lp.domain", "com.example.lp.dao"})
@SpringBootApplication
public class  LPrutasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LPrutasApplication.class, args);
    }

}
