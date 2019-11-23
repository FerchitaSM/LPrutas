package com.example.lp.dao;

import com.example.lp.domain.UserChatEntity;
import com.example.lp.domain.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserTypeRepository extends JpaRepository<UserTypeEntity,Integer> {
    UserTypeEntity findByType(String type);

}
