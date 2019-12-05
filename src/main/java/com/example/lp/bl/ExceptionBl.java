package com.example.lp.bl;

import com.example.lp.dao.ExceptionRepository;
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
public class ExceptionBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);
    private ExceptionRepository exceptionRepository;
    private UsersBl usersBl;

    @Autowired
    public ExceptionBl(ExceptionRepository exceptionRepository, UsersBl usersBl){
        this.exceptionRepository = exceptionRepository;
        this.usersBl = usersBl;
    }



    public List<String> findAllQuestionMessage() {
        List<String> ret = this.exceptionRepository.findAllQuestionMessage();
        return ret;
    }
    public ReplyKeyboardMarkup findAllQuestionMessage(ReplyKeyboardMarkup keyboardMarkup, Update update) {
        List<KeyboardRow> keyboard = new ArrayList<>();

        List<String> allQuestionMessage = this.exceptionRepository.findAllQuestionMessage();
        for (String x: allQuestionMessage) {
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
        String ret =this.exceptionRepository.findAnswerMessageByQuestionMessage(QuestionMessage);
        return ret;
    }

}
