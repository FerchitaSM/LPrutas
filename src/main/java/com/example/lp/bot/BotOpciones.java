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
    List<String> retornar = new ArrayList();

    public BotOpciones(String call_data) {
        this.call_data = call_data;
    }

    public List<String> lista_opciones ()
    {
        switch (getCall_data()) {
            case "Buscar la ruta de una linea":
                retornar.add("Mi Teleferico");
                retornar.add("Puma Katari");
                break;
            case "Mi Teleferico":
                retornar.add("Linea Blanca");
                retornar.add("Linea Azul");
                retornar.add("Linea Morada");
                retornar.add("Linea Roja");
                retornar.add("Linea Celeste");
                retornar.add("Linea Verde");
                retornar.add("Linea Amarilla");
                retornar.add("Linea Plateada");
                retornar.add("Linea Naranja");
                retornar.add("Linea Cafe");
                break;
            case "Puma Katari":
                retornar.add("Chasquipampa");
                retornar.add("Inca Llojeta");
                retornar.add("Villa Salome");
                retornar.add("Caja Ferroviaria");
                retornar.add("Integradora");
                retornar.add("Irapvi II");
                retornar.add("Achumani");
                break;
            case "Buscar minibuses a mi destino":
                retornar.add("Enviar mi ubicacion");
                break;
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
}


