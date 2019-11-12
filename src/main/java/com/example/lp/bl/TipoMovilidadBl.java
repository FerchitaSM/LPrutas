package com.example.lp.bl;

import com.example.lp.dao.TipoMovilidadRepository;
import com.example.lp.domain.TipoMovilidadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TipoMovilidadBl {
    TipoMovilidadRepository tipomovilidadRepository;

    @Autowired
    public TipoMovilidadBl(TipoMovilidadRepository tipoMovilidadRepository)
    {this.tipomovilidadRepository=tipoMovilidadRepository;}

    public TipoMovilidadEntity findTipoMovilidadById(Integer pk){
        Optional<TipoMovilidadEntity> optional=this.tipomovilidadRepository.findById(pk);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Record cannot found this ID "+pk);
        }
    }
}
