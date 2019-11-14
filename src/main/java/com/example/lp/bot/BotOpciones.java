package com.example.lp.bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BotOpciones {
    String call_data;

    public BotOpciones(String call_data) {
        this.call_data = call_data;
    }

    public List<String> lista_opciones ()
    {
        List<String> retornar = new ArrayList();
        switch (getCall_data()) {
            case "Buscar la ruta de una linea":

                retornar.add("Mi Teleferico");
                retornar.add("Puma Katari");
            case "Buscar paradas de minibuses cercanas":
                retornar.add("Enviar mi ubicacion");
            case "Enviar mi ubicacion":
                break;
            default:
                retornar.add("Buscar la ruta de una linea");
                retornar.add("Buscar minibuses a mi destino");
        }
        return retornar;
    }
    public String getCall_data() {
        return call_data;
    }

    public void Calculardistancia(String ubicacion_inicio,String ubicacion_llegada) throws InterruptedException, ApiException, IOException {
        String origen="-16.488812,-68.137212";
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
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
         System.out.println(gson.toJson(distancia));
    }

}


