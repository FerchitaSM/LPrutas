package com.example.lp.bl;
import com.example.lp.dao.TransportRepository;
import com.example.lp.domain.TransportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransportBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);

    private TransportRepository transportRepository;

    @Autowired
    public TransportBl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public TransportEntity findTransportById(int id) {
        Optional<TransportEntity> optional = this.transportRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }

    public String findURLTransportByName(String name) {
        String ret = "https://urgente.bo/sites/default/files/Ruta%20San%20Pedro-%20Achumani%201.jpg";
        List<TransportEntity> list = this.transportRepository.findAll();
        for ( TransportEntity x: list) {
            LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!....................................................................");
            LOGGER.info(String.valueOf(x.getDescription()));
            if(x.getDescription().equals(name))
                ret=x.getRouteImage();
        }
        return ret;
    }
}