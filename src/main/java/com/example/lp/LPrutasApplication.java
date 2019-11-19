package com.example.lp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = { "com.example.lp.dao","com.example.lp.bot","com.example.lp.bl"})
//@EnableJpaRepositories(basePackages = {"com.example.lp.domain", "com.example.lp.dao"})
@SpringBootApplication
public class LPrutasApplication {

    public static void main(String[] args) {
        SpringApplication.run(LPrutasApplication.class, args);
    }
}


