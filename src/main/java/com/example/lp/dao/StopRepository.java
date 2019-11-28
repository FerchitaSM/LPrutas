package com.example.lp.dao;

import com.example.lp.domain.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StopRepository extends JpaRepository<StopEntity,Integer> {
    List<StopEntity> findPointsRoute(int routeIdRoute);
    List<StopEntity> findStartPosition(int idRoute);
    List<StopEntity> findFinishPosition(int idRoute);


    @Query( value  =  " SELECT latitude FROM stop st, route ro, route_stop_transport rst, transport tr" +
            " WHERE st.id_stop = ro.stop_start" +
            "     AND ro.id_route = rst.route_id_route" +
            "     AND rst.transport_id_transport = tr.id_transport"+
            "     AND tr.description =?1",
            nativeQuery  =  true )
    List<String> findLatitudeByDescription (String name);

    @Query( value  =  " SELECT longitude FROM stop st, route ro, route_stop_transport rst, transport tr" +
            " WHERE st.id_stop = ro.stop_start" +
            "     AND ro.id_route = rst.route_id_route" +
            "     AND rst.transport_id_transport = tr.id_transport"+
            "     AND tr.description =?1",
            nativeQuery  =  true )
    List<String> findLongitudeByDescription (String name);

}
