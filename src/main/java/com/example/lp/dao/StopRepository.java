package com.example.lp.dao;

import com.example.lp.domain.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StopRepository extends JpaRepository<StopEntity,Integer> {
}
