package com.example.lp.bot;

import com.example.lp.bl.TransportBl;
import com.example.lp.bl.TransportInfoBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotOpciones {
    private static final Logger log = LoggerFactory.getLogger(BotOpciones.class);
    String call_data;
    Update update;
    List<String> retornar = new ArrayList();
    List<String> mostrar = new ArrayList();

    TransportBl transportBl;
    TransportInfoBl transportInfoBl;

    @Autowired
    public BotOpciones(String call_data,TransportBl transportBl, TransportInfoBl transportInfoBl,Update update) {
        this.call_data = call_data;
        this.transportBl=transportBl;
        this.transportInfoBl= transportInfoBl;
        this.update=update;
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
                    retornar.add("Enviar mi ubicacion");
                    retornar.add("Enviar a donde quiero llegar");
                    break;
                case "Enviar mi ubicacion" :

                    /*SendMessage message=new SendMessage();
                    long chat_id = update.getMessage().getChatId();
                    message.setChatId(chat_id);
                    String call_data=update.getMessage().getText();
                    while((update.hasMessage() && update.getMessage().hasLocation()))
                    if(update.hasMessage() && update.getMessage().hasLocation()){
                        message.setText("hola");
                    }
                    BootMain bo=new BootMain(transportBl,transportInfoBl);
                    try {
                        log.info("mensaje enviado");
                        bo.execute(message);
                    } catch (TelegramApiException e) {
                        log.info("error");
                        e.printStackTrace();
                    }*/

                            /*String chatId = getUpdate().getCallbackQuery().getFrom().getId().toString();
                            SendMessage message = new SendMessage() // Create a message object object
                                    .setChatId(chatId)
                                    .setText("Envia tu ubicacion");
                            BootMain bo=new BootMain(transportBl,transportInfoBl);
                            try {
                                bo.execute(message);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }*/
                          /*  Boolean esperar=false;
                            while(esperar==false){
                                if(getUpdate().getMessage().hasLocation()){
                                    String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
                                    String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
                                    log.info("LA UBICACION ESSSSSSSSSSSSSSSSSSSSS:     "+latitud);
                                    log.info(longitud);
                                    break;
                                }
                            }*/

                   // log.info("LLEGUE AQUI");


                    /*Boolean bandera=false;
                    while(bandera!=true){
                        log.info("llegueeeeeeeeeeeeeeeeeeeee2");
                       /* Message message1=getUpdate().getMessage();
                        if(message1!=null){
                            bandera=true;
                        }*/
                      /*  try {
                            getUpdate().wait();
                            getUpdate().getMessage();
                            log.info("holu");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        /*if(getUpdate().getMessage().getText().equals("h")){
                            log.info("llegueeeeeeeeeeeeeeeeeeee5");
                            bandera=true;
                        }
                        if(getUpdate().getMessage().hasLocation()){
                                log.info("llegueeeeeeeeeeeeeeeeeeee3");
                                String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
                                String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
                                log.info("LA UBICACION ESSSSSSSSSSSSSSSSSSSSS:     "+latitud);
                                log.info(longitud);
                                bandera=true;
                        }*/
                  //  }
                 /*  if(update.getMessage().hasLocation()){

                       /* String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
                        String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
                        log.info("LA UBICACION ESSSSSSSSSSSSSSSSSSSSS:     "+latitud);
                        log.info(longitud);*/
                   // }
                    break;
                case "Enviar a donde quiero llegar":
                    break;

                default:
                    retornar.add("Buscar la ruta de una linea");
                    retornar.add("Buscar minibuses a mi destino");
                    retornar.add("Enviar mi ubicacion");
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
    public Update getUpdate(){return update;}




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




