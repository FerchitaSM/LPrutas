package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.dao.RouteStopRepository;
import com.example.lp.dao.StopRepository;
import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.RouteStopEntity;
import com.example.lp.domain.StopEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteBl.class);
    private static final String tinyUrl = "http://tinyurl.com/api-create.php?url=";
    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;
    private StopRepository stopRepository;


    @Autowired
    public RouteBl( RouteRepository routeRepository,RouteStopRepository routeStopRepository,StopRepository stopRepository) {
        this.routeRepository = routeRepository;
        this.routeStopRepository=routeStopRepository;
        this.stopRepository=stopRepository;
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
    public String drawMap(int route) throws IOException {
        List<String> coordinates=new ArrayList<>();
            List<StopEntity> all = this.stopRepository.findPointsRoute(route);
            for(StopEntity x:all){
                float latitude= (float) x.getLatitude();
                float longitude= (float) x.getLongitude();
                coordinates.add(latitude+","+longitude);
                LOGGER.info(latitude+","+longitude);
            }
        String url=get_url(route,coordinates);
        String short_url=shorter(url);
        return short_url;
    }
    public String origin_and_destin(int route){
        String origin="";
        List<StopEntity> all = this.stopRepository.findStartPosition(route);
        for(StopEntity x:all){
            origin=x.getLatitude()+","+x.getLongitude();
        }
        String destination="";
        List<StopEntity> two_all = this.stopRepository.findFinishPosition(route);
        for(StopEntity x:two_all){
            destination=x.getLatitude()+","+x.getLongitude();
        }
        return origin+"/"+destination;
    }

    public String get_url(int route,List<String> coordinates){
        String waypoint="";
        String ubication=origin_and_destin(route);
        String points[]=ubication.split("/");
        String origin=points[0];
        String destination=points[1];
        String url="https://www.google.com/maps/dir/?api=1&origin=+"+origin+"&destination="+destination+"&travelmode=driving&waypoints=";
       // https://www.google.com/maps/dir/?api=1&origin=Paris,France&destination=Cherbourg,France&travelmode=driving&waypoints=Versailles,France%7CCaen,France%7CLe+Mans,France%7CChartres,France
        for(int i=0;i<coordinates.size();i++){
            String coordin=coordinates.get(i);
            waypoint=waypoint+coordin+"%7C";
        }
        url=url+waypoint;
        return url;
    }
    public String shorter(String url) throws IOException {
        String tinyUrlLookup = tinyUrl + url;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
        String tinyUrl = reader.readLine();
        return tinyUrl;
    }
}