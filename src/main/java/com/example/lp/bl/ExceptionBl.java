package com.example.lp.bl;

import com.example.lp.dao.ExceptionRepository;
import com.example.lp.domain.ExceptionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);
    private ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionBl(ExceptionRepository exceptionRepository){
        this.exceptionRepository = exceptionRepository;
    }
    public List<String> findAllQuestionMessage() {
        List<ExceptionEntity> all = this.exceptionRepository.findAll();
        List<String> ret = new ArrayList<>();
        for (ExceptionEntity x: all) {
            ret.add(x.getQuestionMessage());
        }
        return ret;
    }

}
