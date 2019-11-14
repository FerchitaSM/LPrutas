package com.example.lp.bl;

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

import java.io.IOException;

public class CalcularDistanciaBl {
    private static final Logger logger = LoggerFactory.getLogger(CalcularDistanciaBl.class);
    public String Calculardistancia(String inicio, String llegada) throws InterruptedException, ApiException, IOException {
        String dist="";
        String origen=inicio;
        String destino="-16.495663,-68.133407";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCW_1tL---epCMy6Wix2JrgNWcNjJfqmzg")
                .build();
        //se envia las coordenadas para calcular las distancias
        DistanceMatrix distancia= DistanceMatrixApi.getDistanceMatrix(
                context,
                new String[]{origen},
                new String[]{destino}
        ).mode(TravelMode.WALKING).await();
        Gson gson = new Gson();
        logger.info("Reading JSON file from Java program");
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
        System.out.println(minutos+" minutos");
        return dist;
    }

}
