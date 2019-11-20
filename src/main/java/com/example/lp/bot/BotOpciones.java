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

        List<String> listasTransportInfo =transportInfoBl.findAllDescriptiontransportInfo();
        List<String> listasTransport  =transportBl.findAllDescriptiontransport();

        for(int i =0 ; i<listasTransportInfo.size(); i++)
        {
            if (listasTransportInfo.get(i).equals(getCall_data()))
            {
                int id= transportInfoBl.findIdTransportInfoByName(listasTransportInfo.get(i));
                sacar_TransportePorInfo(id);
                break;
            }
        }

        for(int i =0 ; i<listasTransport.size(); i++)
        {
            if (listasTransport.get(i).equals(getCall_data()))
            {
                mostrar.add(sacar_url(listasTransport.get(i)));
                break;
            }
        }

        if(retornar.size()==0 && mostrar.size()==00) {
            switch (getCall_data()) {
                case "Buscar la ruta de una linea":
                    sacar_TransporteInfo();
                    break;
                case "Buscar minibuses a mi destino":
                    break;
                default:
                    retornar.add("Buscar la ruta de una linea");
                    retornar.add("Buscar minibuses a mi destino");
            }
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

    private void sacar_TransportePorInfo(int info_id){
        retornar=transportBl.findAllDescriptiontransportByInfo(info_id);
    }



    private String sacar_url(String lugar) {
        String ret = "https://urgente.bo/sites/default/files/Ruta%20San%20Pedro-%20Achumani%201.jpg";
        ret =transportBl.findURLTransportByName(lugar);
        return ret;
    }


}




