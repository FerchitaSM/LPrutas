package com.example.lp.bl;

import com.example.lp.dao.UserChatRepository;
import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.TransportEntity;
import com.example.lp.domain.UserChatEntity;
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


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UsersBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBl.class);
    private UsersRepository usersRepository;
    private UserChatRepository userChatRepository;

    @Autowired
    public UsersBl(UsersRepository usersRepository, UserChatRepository userChatRepository) {
        this.usersRepository = usersRepository;
        this.userChatRepository = userChatRepository;
    }


    public boolean existingUser(int chat_id_bot) {
        boolean ret= false;
        List<UsersEntity> list = this.usersRepository.findAll();
        for ( UsersEntity x: list) {
            LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!....................................................................");
            if(x.getIdUserBot()==(chat_id_bot))
                ret=true;
        }
        return ret;
    }


    public UsersEntity findUserById(int id) {
        UsersEntity usersEntity= this.usersRepository.findByIdUserBot(id);
        if (usersEntity!= null) {
            return usersEntity;
        } else {
            throw new RuntimeException("Record cannot found for UsersEntity with ID: " + id);
        }
    }

    public UserDto registrerUser (User users) {
        Date sDate = getDate();
        UsersEntity usersEntity = new UsersEntity();
        usersEntity = new UsersEntity();
        usersEntity.setIdUserBot(users.getId());
        usersEntity.setuStatus(Status.ACTIVE.getStatus());
        usersEntity.setTxHost("localhost");
        usersEntity.setTxUser("admin");
        usersEntity.setTxDate(sDate);
        usersEntity.setUserName(users.getFirstName()+"_"+ users.getLastName());
        usersRepository.save(usersEntity);
        UserDto userDto = new UserDto(usersEntity);
        return userDto;
    }

    public UserChatDto continueWhitUser (Update update, List<String> chatResponse){
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        UsersEntity usersEntity = findUserById(chat_id);

        UserChatEntity lastmessage =userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        String response ="Inicio";

        if( lastmessage!=null)
            response = String.valueOf(lastmessage.getInMessage());

        Date sDate= getDate();

        UserChatEntity userChatEntity = new UserChatEntity();
        userChatEntity.setIdUser(usersEntity.getIdUser());
        userChatEntity.setInMessage(update.getMessage().getText());
        userChatEntity.setOutMessage(response); //TODO FALTABA PONER EL DATO DE RESPUESTA
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
        Date sDate =  convertUtilToSql(uDate);
        return sDate;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
