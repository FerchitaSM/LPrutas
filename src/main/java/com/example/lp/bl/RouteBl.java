package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.domain.RouteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteBl.class);

    private RouteRepository routeRepository;

    @Autowired
    public RouteBl( RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RouteEntity findRouteById(int id) {
        Optional<RouteEntity> optional = this.routeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }

    public List<RouteEntity> findAllrouteInfo() {
        List<RouteEntity> ret = this.routeRepository.findAll();
        return ret;
    }

    public void findAllDescriptionroute() {
        List<RouteEntity> all = this.routeRepository.findAll();
        //List<String> ret = new ArrayList<>();
        for (RouteEntity x: all) {
            //LOGGER.info("id route"+x.getIdRoute());
            LOGGER.info("START+ "+x.getStopStart()+"  FINISH "+x.getStopFinish());
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