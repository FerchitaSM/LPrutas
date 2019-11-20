package com.example.lp.dao;

import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.RouteStopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RouteStopRepository extends JpaRepository <RouteStopEntity,Integer> {
    List<RouteStopEntity> findRoute(int stopIdStop);
    List<RouteStopEntity> findRouteFinish(@Param("routeIdRoute") int idRouteStop,@Param("stopIdStop") int stopIdStop);
}
