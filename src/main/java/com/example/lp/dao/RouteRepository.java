package com.example.lp.dao;

import com.example.lp.domain.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository <RouteEntity,Integer> {
}
