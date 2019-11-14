package com.example.lp.bl;

import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.UsersEntity;
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

    private UsersRepository UsersRepository;

    @Autowired
    public BotBl(UsersRepository UsersRepository) {
        this.UsersRepository = UsersRepository;
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


    private void coninueChatWithUSer(UsersEntity userEntity) {
        // Ver donde se quedo el Usuario
        // continuear co conversacion
    }

    private boolean initUser(User user) {
        boolean result = false;
        UsersEntity usersEntity = UsersRepository.findByBotUserId(user.getId().toString());
        if (usersEntity == null) {
            usersEntity = new UsersEntity();
            usersEntity.setIdUser(user.getId());
            usersEntity.setuStatus(Status.ACTIVE.getStatus());
            usersEntity.setTxHost("localhost");
            usersEntity.setTxUser("admin");
            usersEntity.setTxDate((java.sql.Date) new Date());
            usersEntity.setUserName("xd");
            UsersRepository.save(usersEntity);
            result = true;
        }
        return result;
    }

}