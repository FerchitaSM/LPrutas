package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxiRepository extends JpaRepository<ExceptionEntity,Integer> {
    @Query( value  =  " SELECT zone_t FROM parada_taxi" , nativeQuery  =  true )
    List<String> findAllZones();

    @Query( value  =  " SELECT company_name FROM parada_taxi WHERE zone_t=?1" , nativeQuery  =  true )
    String findAllCompanyNameByZone(String zone);

    @Query( value  =  " SELECT address FROM parada_taxi WHERE company_name=?1" , nativeQuery  =  true )
    String findInfoByCompanyName(String company_name);
}
