package com.example.lp.bot;

import com.example.lp.bl.BotBl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

public class BotMain extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(BotMain.class);

    BotBl botBl;

    public BotMain(BotBl customerBl) {
        this.botBl = customerBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        update.getMessage().getFrom().getId();
        if (update.hasMessage() && update.getMessage().hasLocation()) {
/*            List<String> messages = botBl.processUpdate(update);
            for(String messageText: messages) {*/
                SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                        .setChatId(update.getMessage().getChatId())
                        ;

               //OBTENCION DE LA UBICACION DEL USUARIO
               String latitud=String.valueOf(update.getMessage().getLocation().getLatitude());
               String longitud=String.valueOf(update.getMessage().getLocation().getLongitude());
               String ubi_inicio=latitud+","+longitud;
               String ubi_final="";
                try {
                    Calculardistancia(ubi_inicio,ubi_final);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.setText("Esta es la distancia caminando ");

                try {
                    this.execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            //}
        }
    }

    @Override
    public String getBotUsername() {
        return "Rutas_La_Paz_Bot";
    }
    @Override
    public String getBotToken() {
        return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        // creence su chat bot para que podamos correr en conjunto si
    }
    public void Calculardistancia(String ubicacion_inicio,String ubicacion_llegada) throws InterruptedException, ApiException, IOException {
        String dist="";
        String origen=ubicacion_inicio;
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


