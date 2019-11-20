package com.example.lp.dao;

import com.example.lp.domain.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository <RouteEntity,Integer> {
   // List<RouteEntity> findRoute(int idRoute);
}
