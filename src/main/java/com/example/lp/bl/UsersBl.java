package com.example.lp.bl;

import com.example.lp.dao.UserChatRepository;
import com.example.lp.dao.UserTypeTepository;
import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.UserChatEntity;
import com.example.lp.domain.UserTypeEntity;
import com.example.lp.domain.UsersEntity;
import com.example.lp.dto.Status;
import com.example.lp.dto.UserChatDto;
import com.example.lp.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.transaction.Transactional;
import java.security.SecureRandom;



import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Service
public class UsersBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBl.class);
    private UsersRepository usersRepository;
    private UserChatRepository userChatRepository;
    private UserTypeTepository userTypeTepository;

    @Autowired
    public UsersBl(UsersRepository usersRepository, UserChatRepository userChatRepository, UserTypeTepository userTypeTepository) {
        this.usersRepository = usersRepository;
        this.userChatRepository = userChatRepository;
        this.userTypeTepository = userTypeTepository;
    }


    public boolean existingUser(int chat_id_bot) {
        boolean ret = false;
        List<UsersEntity> list = this.usersRepository.findAll();
        for (UsersEntity x : list) {
            if (x.getIdUserBot() == (chat_id_bot))
                ret = true;
        }
        return ret;
    }



    public UsersEntity findByIdUser(int id) {
        LOGGER.info("findByIdUser.........................");
        UsersEntity usersEntity = this.usersRepository.findByIdUser(id);
        if (usersEntity != null) {
            return usersEntity;
        } else {
            throw new RuntimeException("Record cannot found for UsersEntity with ID: " + id);
        }
    }

    public UsersEntity findByIdUserBot(int id) {
        UsersEntity usersEntity = this.usersRepository.findByIdUserBot(id);
        if (usersEntity != null) {
            return usersEntity;
        } else {
            throw new RuntimeException("Record cannot found for UsersEntity with ID: " + id);
        }
    }

    public UsersEntity registrerUser(User users) {
        Date sDate = getDate();
        UsersEntity usersEntity = new UsersEntity();
        usersEntity = new UsersEntity();
        usersEntity.setIdUserBot(users.getId());
        usersEntity.setIdUserType(1);
        usersEntity.setuStatus(Status.ACTIVE.getStatus());
        usersEntity.setTxHost("localhost");
        usersEntity.setTxUser("fer");
        usersEntity.setTxDate(sDate);
        usersEntity.setUserName(users.getFirstName());
        usersRepository.save(usersEntity);
        return usersEntity;
    }

    public UserChatDto continueWhitUser(Update update, List<String> chatResponse) {
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        UsersEntity usersEntity = findByIdUserBot(chat_id);

        UserChatEntity lastmessage = userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        String response = "Inicio";

        if (lastmessage != null)
            response = String.valueOf(lastmessage.getInMessage());

        Date sDate = getDate();

        UserChatEntity userChatEntity = new UserChatEntity();
        userChatEntity.setIdUser(usersEntity.getIdUser());
        userChatEntity.setInMessage(update.getMessage().getText());
        userChatEntity.setOutMessage(response); //TODO FALATA PONER EL DATO DE RESPUESTA
        userChatEntity.setMsgDate(sDate);
        userChatEntity.setTxUser(update.getMessage().getFrom().getId().toString());
        userChatEntity.setTxHost(update.getMessage().getChatId().toString());
        userChatEntity.setTxDate(sDate);
        // Guardamos en base dedatos
        userChatRepository.save(userChatEntity);
        UserChatDto userChatDto = new UserChatDto(userChatEntity);

        chatResponse.add(userChatEntity.getInMessage());
        return userChatDto;
    }


    private Date getDate() {
        java.util.Date uDate = new java.util.Date();
        Date sDate = convertUtilToSql(uDate);
        return sDate;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public UserTypeEntity getTypeAdministrador() {
        LOGGER.info("UserTypeEntity.........................");
        UserTypeEntity userTypeEntity = this.userTypeTepository.findByType("Administrador");
        return userTypeEntity;
    }


    public String getTokenAdministrador(){
        String ret="";
        UserTypeEntity userTypeEntity = getTypeAdministrador();
        ret= userTypeEntity.getToken();
        return ret;
    }
    @Transactional
    public String changeTypeUser(UsersEntity usersEntity, String tokenUser) {
        String ret="";
        LOGGER.info("changeTypeUser.........................");
        UserTypeEntity userTypeEntity = getTypeAdministrador();
        LOGGER.info("getTypeAdministrador.........................");
        if(tokenUser.equals(userTypeEntity.getToken())) {
            LOGGER.info("token accepted.........................");
            usersEntity.setIdUserType(0);
            usersRepository.save(usersEntity);
            changeToken(userTypeEntity);
            ret="Usted ya es un administrador";
            //Date.from(Instant.now());
        }else {
            LOGGER.info("token denied.........................");
            ret="El token ingresado es incorrecto intente nuevamente";
        }
        return ret;
    }

    @Transactional
    public void changeToken(UserTypeEntity userTypeEntity) {
        String token = tokenGenerator();
        userTypeEntity.setToken(token);
        userTypeTepository.delete(userTypeEntity);
        userTypeTepository.save(userTypeEntity);

    }


    public String tokenGenerator (){
        String token = "";
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        try {
            token = new String(bytes, "UTF-8"); // for UTF-8 encoding
        } catch (UnsupportedEncodingException e) {
            token = "0";
            e.printStackTrace();
        }
        return token;
    }



}