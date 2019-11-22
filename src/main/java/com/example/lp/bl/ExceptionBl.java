package com.example.lp.bl;

import com.example.lp.dao.ExceptionRepository;
import com.example.lp.domain.ExceptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExceptionBl {

    private ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionBl(ExceptionRepository exceptionRepository) {
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
