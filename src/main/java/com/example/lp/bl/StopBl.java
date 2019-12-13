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
        List<Integer> stop_distance=new ArrayList<>();
        Integer contador=0;
        //Se obtiene todas las paradas para luego compararlas con la posicion enviada
        /*Se cuenta la cantidad de datos que hay en stop Repositoty ya que la solicitud de Distance Matrix solo admite 100*/
        int count= (int) this.stopRepository.count();
        /*Se divide para saber cuantas solicitudes habra que hacer*/
        int div=(count/100);
        /*ya que es la cantidad de solicitudes aunque sea mayor a 0 habra una solicitud*/
        div=div+1;
        for(int u=0;u<div;u++){
            int less=u*100;
            /*es 101 ya que estamos utilizando between*/
            int greater=less+101;
            /*se le da un limite con less y greater*/
            List<StopEntity> all = this.stopRepository.findAllById(less,greater);
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
        }
        //se retorna la lista de paradas
        return stop_distance;
    }
    private List<Integer> distancematrix(String origen,String destinos) throws IOException {
        //se abre el contexto para construir la consulta
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCW_1tL---epCMy6Wix2JrgNWcNjJfqmzg")
                .build();
        DistanceMatrix distancia= null;

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
        List<Integer> locaciones=location(distancia);
        //se retorna la lista de posiciones en la que se encuentran las direcciones las cuales su distancia es menor a 30 minutos
        return locaciones;
    }
    //se obtiene la lista de ubicaciones
    private List<Integer> location(DistanceMatrix distancia) throws IOException {
        List<Integer> locaciones=new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Reading JSON file from Java program");
        String contents = gson.toJson(distancia);
        ObjectMapper mapper=new ObjectMapper();
        //se comienza a manejar el JSON por nodos para obtener el valor de la distancia
        JsonNode jsonNode=mapper.readTree(contents);
        JsonNode rows=jsonNode.path("rows");
        //el contador sirve para saber en que locacion se encuentra las direcciones que tiene distancias menores a 30 minutos
        int contador=0;
        for(JsonNode node: rows){
            JsonNode elements=node.path("elements");
            for(JsonNode node2: elements){
                JsonNode duration=node2.path("duration");
                int tiempo=duration.path("inSeconds").asInt();
                Double minutes=((double)tiempo)/60;
                //Si la caminata del origen al destino es menor a 30 minutos se devuelve
                if(minutes<15){
                    locaciones.add(contador);
                }
                contador++;
            }
        }
        return locaciones;
    }


}