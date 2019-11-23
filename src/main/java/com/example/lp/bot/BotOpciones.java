package com.example.lp.bot;

import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
import com.example.lp.bl.UsersBl;
import com.example.lp.domain.UserTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class BotOpciones {

    String call_data;
    List<String> retornar = new ArrayList();
    String mostrar ="";

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;
    UsersBl usersBl;

    @Autowired
    public BotOpciones(String call_data,TransportBl transportBl, TransportInfoBl transportInfoBl, UsersBl usersBl) {
        this.call_data = call_data;
        this.transportBl=transportBl;
        this.transportInfoBl= transportInfoBl;
        this.usersBl=usersBl;
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
                mostrar = (sacar_url(listasTransport.get(i)));
                break;
            }
        }

        if(retornar.size()==0 && mostrar.equals("")) {
            switch (getCall_data()) {
                case "Token":
                    mostrar=("Token");
                    break;
                case "Buscar la ruta de una linea":
                    sacar_TransporteInfo();
                    break;
                case "Buscar minibuses a mi destino":
                    mostrar=("Karen");
                case "Ayuda":
                    sacar_preguntas();
                    break;
                default:
                    retornar.add("Buscar la ruta de una linea");
                    retornar.add("Buscar minibuses a mi destino");
                    retornar.add("Ayuda");
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

    public String getMostrar() {
        return mostrar;
    }


    private void sacar_preguntas() {
        String respuesta= "Debes sacar la respuesta de la BD";
        //AQUI SACAS LA RESPUESTA
        // en el bl de excepciones haces un metodo buscar por question y devuelves la respuesta
        // es como la funcion  sacar_TransporteInfo();
        //retornar =exceptionBl.findAllQuestionMessage();
        retornar.add("lllllllllll");
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




