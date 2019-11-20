package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.dao.RouteStopRepository;
import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.RouteStopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteBl.class);

    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;


    @Autowired
    public RouteBl( RouteRepository routeRepository,RouteStopRepository routeStopRepository) {
        this.routeRepository = routeRepository;
        this.routeStopRepository=routeStopRepository;
    }

    public void findAllroute() {
        List<RouteStopEntity> all = this.routeStopRepository.findRouteFinish(9,3);
        LOGGER.info("LLEGUEEEEEEEEEEEEEEE 1");
        for (RouteStopEntity x: all) {
            LOGGER.info("llegueeeeeeeeeee 2 ");
            //LOGGER.info("id route"+x.getIdRoute());
            LOGGER.info("La ruta "+x.getRouteIdRoute()+" Stop "+x.getStopIdStop()+"");
        }
    }

    public int findRoute(List<Integer> start_points, List<Integer> finish_points){
        int route=0;
        for(int i=0; i<start_points.size();i++){
            System.out.println(start_points.size()+"");
            int point_start=start_points.get(i);
            LOGGER.info("codigo puntos de inicio   "+point_start);
            List<RouteStopEntity> all = this.routeStopRepository.findRoute(point_start);
            for(RouteStopEntity x:all){
                int route_start=x.getRouteIdRoute();
                LOGGER.info("codigo puntos ruta a la que pertenece"+route_start);
                int point_finish=finish_points.get(i);
                LOGGER.info("OBTENIENDO PUNTO DE FINISH DE LA LISTA FISNIS"+point_finish);
                List<RouteStopEntity> find_route=this.routeStopRepository.findRouteFinish(route_start,point_finish);
                for(RouteStopEntity y:find_route){
                    route=y.getRouteIdRoute();
                }
            }

        }
        return route;
    }



    public void findAllDescriptionroute() {
        List<RouteEntity> all = this.routeRepository.findAll();
        //List<String> ret = new ArrayList<>();
        for (RouteEntity x: all) {
            //LOGGER.info("id route"+x.getIdRoute());
           // LOGGER.info("START+ "+x.getStopStart()+"  FINISH "+x.getStopFinish());
        }
    }
  /*  public  List<String> findAllDescriptionroute() {
        List<RouteEntity> all = this.routeRepository.findAll();
        List<String> ret = new ArrayList<>();
        for (RouteEntity x: all) {
            ret.add(x.getRouteDetails());
        }
        return ret;
    }
    /*public int findIdTransportInfoByName(String name) {
        int ret=0;
        List<RouteEntity> list = this.routeRepository.findAll();
        for ( RouteEntity x: list) {
            if(x.getIdRoute().equals(name))
                ret=x.get();
        }
        return ret;
    }
*/

}