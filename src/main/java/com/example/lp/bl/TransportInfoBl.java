package com.example.lp.bl;


import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.domain.TransportEntity;
import com.example.lp.domain.TransportInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportInfoBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);

    private TransportInfoRepository transportInfoRepository;

    @Autowired
    public TransportInfoBl( TransportInfoRepository transportInfoRepository) {
        this.transportInfoRepository = transportInfoRepository;
    }

    public TransportInfoEntity findTransportInfoById(int id) {
        Optional<TransportInfoEntity> optional = this.transportInfoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }

    public  List<TransportInfoEntity> findAlltransportInfo() {
        List<TransportInfoEntity> ret = this.transportInfoRepository.findAll();
        return ret;
    }

    public  List<String> findAllDescriptiontransportInfo() {
        List<TransportInfoEntity> all = this.transportInfoRepository.findAll();
        List<String> ret = new ArrayList<>();
        for (TransportInfoEntity x: all) {
            ret.add(x.getInfoDescription());
        }
        return ret;
    }
    public int findIdTransportInfoByName(String name) {
        int ret=0;
        List<TransportInfoEntity> list = this.transportInfoRepository.findAll();
        for ( TransportInfoEntity x: list) {
            if(x.getInfoDescription().equals(name))
                ret=x.getIdTransportInfo();
        }
        return ret;
    }


}
