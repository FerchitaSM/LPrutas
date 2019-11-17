package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.dao.StopRepository;
import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.StopEntity;
import com.example.lp.domain.TransportInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StopBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopBl.class);

    private StopRepository stopRepository;

    @Autowired
    public StopBl( StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public StopEntity findStopById(int id) {
        Optional<StopEntity> optional = this.stopRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }

    public List<StopEntity> findAllrouteInfo() {
        List<StopEntity> ret = this.stopRepository.findAll();
        return ret;
    }

    public void findAllNearbyLocationStop() {
        List<StopEntity> all = this.stopRepository.findAll();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            String parada=x.getDescription();
            LOGGER.info("ubicacion  parada"+parada+"+"+latitud+"/"+longitud);
        }
    }
    /*
    public  List<Integer> findAllNearbyLocationStop(String location) {
        List<StopEntity> all = this.stopRepository.findAll();
        List<Integer> ret = new ArrayList<>();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            CalcularDistancia(location,latitud+","+longitud);
            ret.add(x.getIdStop());
        }
        return ret;
    }
    public void CalcularDistancia(String location,String nearby_location){

    }*/


}