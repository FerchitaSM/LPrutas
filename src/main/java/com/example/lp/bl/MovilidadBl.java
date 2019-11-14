package com.example.lp.bl;

import com.example.lp.bot.BotInicializator;
import com.example.lp.dao.MovilidadRepository;
import com.example.lp.domain.MovilidadEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovilidadBl {
    private static final Logger log = LoggerFactory.getLogger(MovilidadBl.class);

    MovilidadRepository movilidadRepository;

    @Autowired
    public MovilidadBl(MovilidadRepository movilidadRepository) {
        this.movilidadRepository = movilidadRepository;
    }

    public MovilidadEntity findMovilidadById(Integer pk) {
        Optional<MovilidadEntity> optional = movilidadRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for movilidadEntity with ID: " + pk);
        }
    }
}



