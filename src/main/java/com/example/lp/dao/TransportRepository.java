package com.example.lp.dao;

import com.example.lp.domain.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<TransportEntity,Integer> {
}
