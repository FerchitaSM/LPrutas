package com.example.lp.bot;

import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BotOpciones {

    String call_data;
    List<String> retornar = new ArrayList();
    List<String> mostrar = new ArrayList();

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;

    @Autowired
    public BotOpciones(String call_data,TransportBl transportBl, TransportInfoBl transportInfoBl) {
        this.call_data = call_data;
        this.transportBl=transportBl;
        this.transportInfoBl= transportInfoBl;
        lista_opciones();
    }

    public List<String> lista_opciones ()
    {
        switch (getCall_data()) {
            case "Buscar la ruta de una linea":
                sacar_TransporteInfo();
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
            case "Achumani":
                mostrar.add(sacar_url("Achumani"));
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
    public List<String> getRetornar() {
        return retornar;
    }

    public List<String> getMostrar() {
        return mostrar;
    }





    private void sacar_TransporteInfo(){
        retornar=transportInfoBl.findAllDescriptiontransportInfo();
    }

    private String sacar_url(String lugar) {
        String ret = "https://urgente.bo/sites/default/files/Ruta%20San%20Pedro-%20Achumani%201.jpg";
        ret =transportBl.findURLTransportByName(lugar);
        return ret;
    }


}




