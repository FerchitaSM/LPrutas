package com.example.lp.dao;

import com.example.lp.domain.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransportRepository extends JpaRepository<TransportEntity,Integer> {
    List<TransportEntity> findAllByTransportStatus(int transport_status);
    List<TransportEntity> findTransport(int transportInfoIdTransportInfo);

    @Query( value  =  " SELECT description FROM transport where transport_status =?1" , nativeQuery  =  true )
    List<String> findDescriptionByTransportStatus (int transport_status);

    @Query( value  =  " SELECT description FROM transport where transport_info_id_transport_info= ?1 and transport_status =?2 " , nativeQuery  =  true )
    List<String> findAllDescriptiontransportByInfoAndStatus (int id_transport_info, int transport_status);

    @Query( value  =  "SELECT route_image FROM transport where description=?1 " , nativeQuery  =  true )
    String findRouteImagetransportByDescription (String description);

}
