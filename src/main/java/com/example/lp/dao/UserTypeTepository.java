package com.example.lp.dao;

import com.example.lp.domain.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeTepository extends JpaRepository<UserTypeEntity,Integer> {
    UserTypeEntity findByType(String type);

}