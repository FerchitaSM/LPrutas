package com.example.lp.dao;

import com.example.lp.domain.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StopRepository extends JpaRepository<StopEntity,Integer> {
    List<StopEntity> findPointsRoute(int routeIdRoute);
    List<StopEntity> findStartPosition(int idRoute);
    List<StopEntity> findFinishPosition(int idRoute);
}
