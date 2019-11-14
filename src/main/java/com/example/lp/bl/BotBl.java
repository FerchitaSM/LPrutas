package com.example.lp.bl;

import com.example.lp.dao.UserInfoRepository;
import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.UserInfoEntity;
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

    private UsersRepository usersRepository;
    private UserInfoRepository  userInfoRepository;

    @Autowired
    public BotBl(UsersRepository usersRepository, UserInfoRepository userInfoRepository) {
        this.usersRepository = usersRepository;
        this.userInfoRepository = userInfoRepository;
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

    private boolean initUser(User users) {
        boolean result = false;
        UsersEntity usersEntity = usersRepository.findByUserName(users.getId().toString());
        if (usersEntity == null) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserInfoStatus(Status.ACTIVE.getStatus());
            userInfoEntity.setFirstName(users.getFirstName());
            userInfoEntity.setLastName(users.getLastName());
            userInfoEntity.setTxHost("localhost");
            userInfoEntity.setTxUser("admin");
            userInfoEntity.setTxDate((java.sql.Date) new Date());
            userInfoRepository.save(userInfoEntity);
            usersEntity = new UsersEntity();
            usersEntity.setUserName(users.getId().toString());
            usersEntity.setuStatus(Status.ACTIVE.getStatus());
            usersEntity.setTxHost("localhost");
            usersEntity.setTxUser("admin");
            usersEntity.setTxDate((java.sql.Date) new Date());
            usersRepository.save(usersEntity);
            result = true;
        }
        return result;
    }

}