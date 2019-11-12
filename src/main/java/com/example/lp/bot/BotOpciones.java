package com.example.lp.bot;

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
}


