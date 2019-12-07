package com.example.lp.bot;

import com.example.lp.bl.ExceptionBl;
import com.example.lp.bl.TaxiBl;
import com.example.lp.bl.TransportBl;
import com.example.lp.bl.UsersBl;
import com.example.lp.dao.TaxiRepository;
import com.example.lp.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class BotOpciones {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);

    String call_data;
    List<String> retornar = new ArrayList();
    String mostrar ="";
    TaxiBl taxiBl;
    ExceptionBl exceptionBl;
    TransportBl transportBl;
    UsersBl usersBl;
    UserDto userDto;

    @Autowired
    public BotOpciones(String call_data, TransportBl transportBl, TaxiBl taxiBl, ExceptionBl exceptionBl, UsersBl usersBl, UserDto userDto) {
        this.call_data = call_data;
        this.transportBl=transportBl;
        this.taxiBl=taxiBl;
        this.exceptionBl=exceptionBl;
        this.usersBl=usersBl;
        this.userDto = userDto;
        lista_opciones();
    }

    public void lista_opciones ()
    {
        if(userDto.getIdUserType()==0) {// admi
            opcionesadmi();
        }else {
            opcionesUsuario();
        }
    }
    private void numero_telefono (){

    }

    private void opcionesadmi() {
        if ((getCall_data().equals("Altas")) || getCall_data().equals("Bajas") || getCall_data().equals("Modificaciones")){
            mostrar = "Escriba lo siguiente: Nombre de la tabla / Atributo a cambiar / Nuevo dato";
        }else{
            retornar.add("Altas");
            retornar.add("Bajas");
            retornar.add("Modificaciones");
        }

    }

    private void opcionesUsuario() {
        List<String> listasTransportInfo =transportBl.findAllDescriptiontransportInfo();
        List<String> listasTransport  =transportBl.findAllDescriptiontransport();

        for(int i =0 ; i<listasTransportInfo.size(); i++)
        {
            if (listasTransportInfo.get(i).equals(getCall_data()))
            {
                int id= transportBl.findIdTransportInfoByName(listasTransportInfo.get(i));
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
                case "Buscar la ruta de una linea":
                    sacar_TransporteInfo();
                    break;
                case "Buscar minibuses a mi destino":
                    mostrar=("Karen");
                case "Mostrar estaciones de RadioTaxi disponibles":
                    sacar_taxi();
                case "Mis Rutas":
                    sacar_Preguntas();
                    break;
                case "Ayuda":
                    sacar_Preguntas();
                    break;
                default:
                    String token =usersBl.getTokenAdministrador();
                    if(getCall_data().equals(token)){ //TODO AUNQUE SON IGUALES SALE FALSO
                        mostrar= token;
                        break;
                    }else {
                        retornar.add("Buscar la ruta de una linea");
                        retornar.add("Buscar minibuses a mi destino");
                        retornar.add("Ayuda");
                        /*
                        retornar.add(String.valueOf(getCall_data().equals(token)));
                        retornar.add(getCall_data());
                        retornar.add(token);
                         */
                        break;
                    }
            }
        }
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

    private void sacar_taxi() { retornar=taxiBl.findAllZones(); }
    private void sacar_Preguntas() { retornar=exceptionBl.findAllQuestionMessage(); }

    private void sacar_TransporteInfo(){
        retornar=transportBl.findAllDescriptiontransportInfo();
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




