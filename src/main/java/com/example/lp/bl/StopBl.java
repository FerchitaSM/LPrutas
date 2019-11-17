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

import javax.persistence.criteria.CriteriaBuilder;
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

    public List<Integer> findAllNearbyLocationStop(String location) {
        List<StopEntity> all = this.stopRepository.findAll();
        Boolean bandera=false;
        List<Integer> stop_distance = new ArrayList<>();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            //Colocamos una bandera para saber si la parada cumple con el requisito de distancia
            bandera=CalculateDistance(x.getIdStop(),location,latitud+","+longitud);
            if(bandera==true){
                 stop_distance.add(x.getIdStop());
            }
        }
        return stop_distance;
    }
    public boolean CalculateDistance(int id_stop, String location,String nearby_location){
        Boolean bandera=false;
        //aca se obtiene la ubicacion de origen y destino de los datos entrantes
        String origin=location;
        String destination=nearby_location;
        //Se utiliza la clave de la API de Google
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCW_1tL---epCMy6Wix2JrgNWcNjJfqmzg")
                .build();
        //se envia las coordenadas para calcular las distancias caminando
        DistanceMatrix distancia= null;
        try {
            distancia = DistanceMatrixApi.getDistanceMatrix(
                    context,
                    new String[]{origin},
                    new String[]{destination}
            ).mode(TravelMode.WALKING).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //se genera un Gson
        Gson gson = new Gson();
        System.out.println("Reading JSON file from Java program");
        //Se busca en el JSON el dato de la duracion en segundos dentro del JSON
        String contents = gson.toJson(distancia);
        JSONObject object=new JSONObject(contents);
        JSONArray rows=object.getJSONArray("rows");
        JSONObject first_level =rows.getJSONObject(0);
        JSONArray elements=first_level.getJSONArray("elements");
        JSONObject second_level=elements.getJSONObject(0);
        JSONObject duration=second_level.getJSONObject("duration");
        //Al encontrar el valor se lo convierte a minutos
        Integer valor=duration.getInt("inSeconds");
        Double minutos=((double)valor)/60;
        //Si la caminata del origen al destino es menor a 30 minutos se devuelve
        if(minutos<30){
            bandera=true;
        }
        return bandera;
    }


}