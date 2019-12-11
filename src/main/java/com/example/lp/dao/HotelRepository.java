package com.example.lp.dao;

import com.example.lp.domain.ExceptionEntity;
import com.example.lp.domain.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<HotelEntity,Integer> {
    @Query( value  =  " SELECT name FROM hotel" , nativeQuery  =  true )
    List<String> findAllName();


    @Query( value  =  " SELECT imagen FROM hotel where name =?1" , nativeQuery  =  true )
    String findImagenByName (String name);

    @Query( value  =  " SELECT latitude FROM hotel where name =?1" , nativeQuery  =  true )
    String findLatitudeByName (String name);

    @Query( value  =  " SELECT longitude FROM hotel where name =?1" , nativeQuery  =  true )
    String findLongitudeByName (String name);

}
