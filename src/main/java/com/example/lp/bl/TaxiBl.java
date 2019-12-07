package com.example.lp.bl;

import com.example.lp.dao.TaxiRepository;
import com.example.lp.dao.UserChatRepository;
import com.example.lp.domain.ExceptionEntity;
import com.example.lp.domain.TransportEntity;
import com.example.lp.domain.TransportInfoEntity;
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
public class TaxiBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);
    private TaxiRepository taxiRepository;
    private UsersBl usersBl;

    @Autowired
    public TaxiBl(TaxiRepository taxiRepository, UsersBl usersBl){
        this.taxiRepository = taxiRepository;
        this.usersBl = usersBl;
    }



    public List<String> findAllZones() {
        List<String> ret = this.taxiRepository.findAllZones();
        return ret;
    }
    public ReplyKeyboardMarkup findAllZones(ReplyKeyboardMarkup keyboardMarkup, Update update) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        List<String> allZones = this.taxiRepository.findAllZones();
        for (String x: allZones) {
            KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
            row.add(x);
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        LOGGER.info("findAllDescriptiontransportByInfo.........................");
        return keyboardMarkup;
    }

    public String findAnswerMessageByQuestionMessage(Update update ) {
        long chat_id = update.getMessage().getChatId();
        String QuestionMessage = this.usersBl.idMessage(chat_id);
        String ret =this.taxiRepository.findAnswerMessageByQuestionMessage(QuestionMessage);
        return ret;
    }

}
