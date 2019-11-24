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


    private void coninueChatWithUSer(UsersEntity usersEntity) {
        // Ver donde se quedo el Usuario
        // continuear co conversacion
    }
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    private boolean initUser(User users) {
        boolean result = false;
        UsersEntity usersEntity = usersRepository.findByUserName(users.getId().toString());
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = convertUtilToSql(uDate);
        if (usersEntity == null) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserInfoStatus(Status.ACTIVE.getStatus());
            userInfoEntity.setFirstName(users.getFirstName());
            userInfoEntity.setLastName(users.getLastName());
            userInfoEntity.setTxHost("localhost");
            userInfoEntity.setTxUser("admin");
            userInfoEntity.setTxDate(sDate);
            userInfoRepository.save(userInfoEntity);
            usersEntity = new UsersEntity();
            usersEntity.setUserName(users.getId().toString());
            usersEntity.setuStatus(Status.ACTIVE.getStatus());
            usersEntity.setTxHost("localhost");
            usersEntity.setTxUser("admin");
            usersEntity.setTxDate(sDate);
            usersEntity.setUserName("xd");
            usersRepository.save(usersEntity);
            result = true;
        }
        return result;
    }



}