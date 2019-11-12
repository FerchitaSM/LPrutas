package com.example.lp.bl;

import com.example.lp.bot.BotMain;
import com.example.lp.dao.UserRepository;
import com.example.lp.domain.UserEntity;
import com.example.lp.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BotBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);

    private UserRepository UserRepository;

    @Autowired
    public BotBl(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public List<String> processUpdate(Update update) {
        LOGGER.info("Recibiendo update {} ", update);
        List<String> result = new ArrayList<>();
        // Si es la primera vez pedir una imagen para su perfil
        if (initUser(update.getMessage().getFrom())) {
            LOGGER.info("Primer inicio de sesion para: {} ",update.getMessage().getFrom() );
            result.add("Por favor ingrese una imagen para su foto de perfil");
        } else { // Mostrar el menu de opciones
            LOGGER.info("Dando bienvenida a: {} ",update.getMessage().getFrom() );
            result.add("Bienvenido al Bot");
        }

        //continueChatWihtUser(CpUser, CpChat)


        return result;
    }


    private void coninueChatWithUSer(UserEntity userEntity) {
        // Ver donde se quedo el Usuario
        // continuear co conversacion
    }

    private boolean initUser(User user) {
        boolean result = false;
        UserEntity userEntity = UserRepository.findByBotUserId(user.getId().toString());
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setIdUser(user.getId());
            userEntity.setStatus(Status.ACTIVE.getStatus());
            userEntity.setTextHost("localhost");
            userEntity.setTextUser("admin");
            userEntity.setNameUser("xd");
            UserRepository.save(userEntity);
            result = true;
        }
        return result;
    }

}