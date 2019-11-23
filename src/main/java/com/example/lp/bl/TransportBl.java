package com.example.lp.bl;
import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.dao.TransportRepository;
import com.example.lp.domain.TransportEntity;
import com.example.lp.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TransportBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);

    private TransportRepository transportRepository;
    private TransportInfoRepository transportInfoRepository;

    @Autowired
    public TransportBl(TransportRepository transportRepository, TransportInfoRepository transportInfoRepository) {
        this.transportRepository = transportRepository;
        this.transportInfoRepository = transportInfoRepository;
    }



    public TransportEntity findTransportById(int id) {
        Optional<TransportEntity> optional = this.transportRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }

    // funcion para devolver la descripcion de los atributos de la tabla transporte
    public  List<String> findAllDescriptiontransport() {
        List<String> ret = this.transportRepository.findDescriptionByTransportStatus(Status.ACTIVE.getStatus());
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("Descriptiontransport null");
            throw new RuntimeException("There is no transport with active status");
        }
    }


    // funcion para devolver la descripcion de los atributos de la tabla transporte por id_info
    public  List<String> findAllDescriptiontransportByInfo(int info_id) {
        List<String> ret = this.transportRepository.findAllDescriptiontransportByInfoAndStatus(info_id, Status.ACTIVE.getStatus());
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("findAllDescriptiontransportByInfo null");
            throw new RuntimeException("There is no transport with active status and transpor_info ID:" + info_id);
        }
    }



    public String findURLTransportByName(String name) {
        String ret = this.transportRepository.findRouteImagetransportByDescription(name);
        return ret;
    }



}