package com.example.lp.dao;

import com.example.lp.domain.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<UsersEntity,Integer>{

    UsersEntity findByUserName(String botUserName);
    UsersEntity findByIdUserBot(int botUserId);

}