package com.example.lp.bl;

import com.example.lp.dao.ExceptionRepository;
import com.example.lp.dao.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);
    private HotelRepository hotelRepository;

    @Autowired
    public HotelBl(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public ReplyKeyboardMarkup findAllName(ReplyKeyboardMarkup keyboardMarkup) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        List<String> allName = this.hotelRepository.findAllName();
        for (String x: allName) {
            KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
            row.add(x);
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        LOGGER.info("findAllName.........................");
        return keyboardMarkup;
    }

    public String findImagenByName(Update update) {
        String name = update.getMessage().getText();
        String ret = this.hotelRepository.findImagenByName(name);
        return ret;
    }

    public String findAllInformationHotel(Update update) {
        String name = update.getMessage().getText();
        String ret = this.hotelRepository.findImagenByName(name);
        ret+= "\n\n Quiere saber como llegar al hotel "+name;
        return ret;
    }

    public ReplyKeyboardMarkup GoHotel(ReplyKeyboardMarkup keyboardMarkup, Update update) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();// Creando una fila de teclado

        row.add("Si");
        row.add("No");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        LOGGER.info("GoHotel.........................");
        return keyboardMarkup;


    }

    public String findLatitude(Update update) {
        String name = update.getMessage().getText();
        String ret = this.hotelRepository.findLatitudeByName(name);
        return ret;
    }

    public String findLongitude(Update update) {
        String name = update.getMessage().getText();
        String ret = this.hotelRepository.findLongitudeByName(name);
        return ret;
    }
}
