package com.example.lp.api;



import com.example.lp.bl.MovilidadBl;
import com.example.lp.dao.MovilidadRepository;
import com.example.lp.domain.MovilidadEntity;
import com.example.lp.dto.MovilidadDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/movilidad")
public class MovilidadController {
    private MovilidadBl movilidadBl;

    @Autowired
    public MovilidadController(MovilidadBl movilidadBl) {
        this.movilidadBl = movilidadBl;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    MovilidadDto oneMovilidad(){
        MovilidadDto movilidadDto = new MovilidadDto(movilidadBl.findMovilidadById(0));
        return movilidadDto;
    }
}
