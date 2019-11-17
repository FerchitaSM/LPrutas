package com.example.lp.bl;

import com.example.lp.dao.RouteRepository;
import com.example.lp.dao.StopRepository;
import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.domain.RouteEntity;
import com.example.lp.domain.StopEntity;
import com.example.lp.domain.TransportInfoEntity;
import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.IOException;
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

   /* public void findAllNearbyLocationStop() {
        List<StopEntity> all = this.stopRepository.findAll();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            String parada=x.getDescription();
            LOGGER.info("ubicacion  parada"+parada+"+"+latitud+"/"+longitud);
        }
    }*/
    public  void findAllNearbyLocationStop(String location) {
        List<StopEntity> all = this.stopRepository.findAll();
      //  List<Integer> ret = new ArrayList<>();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            CalcularDistancia(location,latitud+","+longitud,x.getDescription());
           // ret.add(x.getIdStop());
        }
       // return ret;
    }
    public void CalcularDistancia(String location,String nearby_location,String parada){
        String origen=location;
        String destino=nearby_location;
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCW_1tL---epCMy6Wix2JrgNWcNjJfqmzg")
                .build();
        //se envia las coordenadas para calcular las distancias
        DistanceMatrix distancia= null;
        try {
            distancia = DistanceMatrixApi.getDistanceMatrix(
                    context,
                    new String[]{origen},
                    new String[]{destino}
            ).mode(TravelMode.WALKING).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // System.out.println(gson.toJson(distancia));
        Gson gson = new Gson();
        System.out.println("Reading JSON file from Java program");
        //Se busca en el JSON el datos de la duracion en segundos
        String contents = gson.toJson(distancia);
        JSONObject o=new JSONObject(contents);
        JSONArray rows=o.getJSONArray("rows");
        JSONObject nivel_uno =rows.getJSONObject(0);
        JSONArray elements=nivel_uno.getJSONArray("elements");
        JSONObject obje=elements.getJSONObject(0);
        JSONObject b=obje.getJSONObject("duration");
        Integer valor=b.getInt("inSeconds");
        Double minutos=((double)valor)/60;
        System.out.println(parada+"           "+minutos+" minutos");
    }


}