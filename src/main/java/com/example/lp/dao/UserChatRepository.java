package com.example.lp.dao;

import com.example.lp.domain.UserChatEntity;
import com.example.lp.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserChatRepository extends JpaRepository<UserChatEntity,Integer> {

    @Query ( value  =  " SELECT * FROM user_chat where id_user =?1 ORDER BY id_user_chat DESC LIMIT 1 " , nativeQuery  =  true )
    UserChatEntity findLastChatByUserId(Integer userId);

}
