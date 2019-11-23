package com.example.lp.bl;
import com.example.lp.dao.StopRepository;
import com.example.lp.domain.StopEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StopBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopBl.class);

    private StopRepository stopRepository;

    @Autowired
    public StopBl( StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<Integer> findAllNearbyLocationStop(String location) throws IOException {
        Integer contador=0;
        //Se obtiene todas las paradas para luego compararlas con la posicion enviada
        List<StopEntity> all = this.stopRepository.findAll();
        List<Integer> stop_distance = new ArrayList<>();
        String destinos="";
        //En String destinos se almacena las coordenadas que se enviara al url
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            destinos=destinos+latitud+","+longitud+"|";
        }
        //se lo envia a distancematrix y obtiene una lista con enteros de en que ubicacion se encuentran las locaciones cercanas
        List<Integer> locaciones=distancematrix(location,destinos);
        for(StopEntity y:all){
            for(int i=0;i<locaciones.size();i++){
                if(contador == locaciones.get(i)){
                    //se almacena en stop_distance los id`s de las paradas cercanas
                    stop_distance.add(y.getIdStop());
                }
            }
            contador++;
        }
        //se retorna la lista de paradas
        return stop_distance;
    }
    public List<Integer> distancematrix(String origen,String destinos) throws IOException {
        //se abre el contexto para construir la consulta
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCW_1tL---epCMy6Wix2JrgNWcNjJfqmzg")
                .build();
        DistanceMatrix distancia= null;
        //LA URL ADMITE UNOS 100 DATOS ENTRANTES
        // se envia la consulta con el origen y las paradas para medir la distancia
        try {
            distancia = DistanceMatrixApi.getDistanceMatrix(
                    context,
                    new String[]{origen},
                    new String[]{destinos}
            ).mode(TravelMode.WALKING).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Reading JSON file from Java program");
        String contents = gson.toJson(distancia);
        ObjectMapper mapper=new ObjectMapper();
        //se comienza a manejar el JSON por nodos para obtener el valor de la distancia
        JsonNode jsonNode=mapper.readTree(contents);
        JsonNode rows=jsonNode.path("rows");
        List<Integer> locaciones=new ArrayList<>();
        //el contador sirve para saber en que locacion se encuentra las direcciones que tiene distancias menores a 30 minutos
        int contador=0;
        for(JsonNode node: rows){
            JsonNode elements=node.path("elements");
            for(JsonNode node2: elements){
                JsonNode duration=node2.path("duration");
                int tiempo=duration.path("inSeconds").asInt();
                Double minutes=((double)tiempo)/60;
                //Si la caminata del origen al destino es menor a 30 minutos se devuelve
                if(minutes<30){
                    locaciones.add(contador);
                }
                contador++;
            }
        }
        //se retorna la lista de posiciones en la que se encuentran las direcciones las cuales su distancia es menor a 30 minutos
        return locaciones;
    }

    /*
    public List<Integer> findAllNearbyLocationStop(String location) {
        //Se obtiene todas las paradas para luego compararlas con la posicion enviada
        List<StopEntity> all = this.stopRepository.findAll();
        Boolean flag=false;
        List<Integer> stop_distance = new ArrayList<>();
        for (StopEntity x: all) {
            String latitud=String.valueOf(x.getLatitude());
            String longitud= String.valueOf(x.getLongitude());
            //Colocamos una bandera para saber si la parada cumple con el requisito de distancia
            flag=CalculateDistance(location,latitud+","+longitud);
            if(flag==true){
                 stop_distance.add(x.getIdStop());
            }
        }
        return stop_distance;
    }*/
    //Funcion que calcula la distancia
    /*public boolean CalculateDistance(String location,String nearby_location){
        Boolean flag=false;
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
        //Se busca en el JSON el dato de la duracion en segundos dentro del JSON
        String contents = gson.toJson(distancia);
       // JsonNode jsonNode = objectMapper.readTree(json);

        JSONObject object=new JSONObject(contents);
        JSONArray rows=object.getJSONArray("rows");
        JSONObject first_level =rows.getJSONObject(0);
        JSONArray elements=first_level.getJSONArray("elements");
        JSONObject second_level=elements.getJSONObject(0);
        JSONObject duration=second_level.getJSONObject("duration");
        //Al encontrar el valor se lo convierte a minutos
        Integer value=duration.getInt("inSeconds");
        Double minutes=((double)value)/60;
        //Si la caminata del origen al destino es menor a 30 minutos se devuelve
        if(minutes<30){
            flag=true;
        }
        return flag;
    }*/


}