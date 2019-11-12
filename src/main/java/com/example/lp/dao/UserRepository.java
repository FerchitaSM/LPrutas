package com.example.lp.dao;

import com.example.lp.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{

    UserEntity findByBotUserId(String botUserId);
}