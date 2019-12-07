package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxiRepository extends JpaRepository<ExceptionEntity,Integer> {
    @Query( value  =  " SELECT zone_t FROM parada_taxi" , nativeQuery  =  true )
    List<String> findAllZones();

}
