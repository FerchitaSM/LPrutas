package com.example.lp.dao;

import com.example.lp.domain.TransportEntity;
import com.example.lp.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportRepository extends JpaRepository<TransportEntity,Integer> {
}
