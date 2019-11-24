package com.example.lp.dao;

import com.example.lp.domain.TransportInfoEntity;
import com.example.lp.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransportInfoRepository extends JpaRepository<TransportInfoEntity,Integer> {

    List<TransportInfoEntity> findAll();
    TransportInfoEntity findByIdTransportInfo(int transportId);

    @Query( value  =  " SELECT info_description FROM transport_info" , nativeQuery  =  true )
    List<String> findAllDescriptiontransportInfo();

    // funcion para retornar el id del transportInfo segun la descripcion (nombre)
    @Query( value  =  " SELECT id_transport_info FROM transport_info where info_description=?1 " , nativeQuery  =  true )
    int findIdTransportInfoByName(String name);

}