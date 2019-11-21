package com.example.lp.bl;

import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.UsersEntity;
import com.example.lp.dto.Status;
import com.example.lp.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;

@Service
public class UsersBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBl.class);
    private UsersRepository usersRepository;

    @Autowired
    public UsersBl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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

    public boolean registrerUser (User users) {
        boolean ret= false;
        java.util.Date uDate = new java.util.Date();
        Date sDate = convertUtilToSql(uDate);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity = new UsersEntity();
        usersEntity.setIdUserBot(users.getIdUserBot());
        usersEntity.setuStatus(Status.ACTIVE.getStatus());
        usersEntity.setTxHost("localhost");
        usersEntity.setTxUser("fer");
        usersEntity.setTxDate(sDate);
        usersEntity.setUserName(users.getUserName());
        usersRepository.save(usersEntity);
        ret = true;

        return ret;
    }



    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
