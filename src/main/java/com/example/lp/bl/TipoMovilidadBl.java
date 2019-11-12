package com.example.lp.bl;

import com.example.lp.dao.TipoMovilidadRepository;
import com.example.lp.domain.TipoMovilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

public class TipoMovilidadBl {
    TipoMovilidadRepository tipomovilidadRepository;

    public TipoMovilidadBl(TipoMovilidadRepository tipoMovilidadRepository)
    {this.tipomovilidadRepository=tipoMovilidadRepository;}

    public TipoMovilidadEntity findPersonById(Integer pk){
        Optional<TipoMovilidadEntity> optional=this.tipomovilidadRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("No se puede");
        }
    }
}
