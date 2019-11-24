package com.example.lp.bl;
import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.dao.TransportRepository;
import com.example.lp.domain.TransportEntity;
import com.example.lp.domain.TransportInfoEntity;
import com.example.lp.dto.Status;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

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
    //Funciones de Transport

    public ReplyKeyboardMarkup DescriptiontransportInfo(ReplyKeyboardMarkup keyboardMarkup){
        List<KeyboardRow> keyboard = new ArrayList<>();
        List<TransportInfoEntity> all = this.transportInfoRepository.findAll();
        for (TransportInfoEntity x: all) {
            KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
            row.add(x.getInfoDescription());
            LOGGER.info(x.getInfoDescription());
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

   /* public TransportEntity findTransportById(int id) {
        Optional<TransportEntity> optional = this.transportRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + id);
        }
    }*/

    // funcion para devolver la descripcion de los atributos de la tabla transporte
    public  List<String> findAllDescriptiontransport() {
        LOGGER.info("findAllDescriptiontransport.........................");
        List<String> ret = this.transportRepository.findDescriptionByTransportStatus(Status.ACTIVE.getStatus());
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("Descriptiontransport null");
            throw new RuntimeException("There is no transport with active status");
        }
    }
    //MODIFICACION KAREN
    public ReplyKeyboardMarkup findAllDescriptiontransport(ReplyKeyboardMarkup keyboardMarkup, Update update) {
        List<KeyboardRow> keyboard = new ArrayList<>();
        String type_transport=update.getMessage().getText();
        LOGGER.info(type_transport+"");
        List<TransportInfoEntity> all = this.transportInfoRepository.findAll();
        int info_id=0;
        for (TransportInfoEntity x: all) {
            if(type_transport.equals(x.getInfoDescription())){
                info_id=x.getIdTransportInfo();
            }
        }
        List<TransportEntity> transports = this.transportRepository.findTransport(info_id);
        for (TransportEntity x: transports) {
            LOGGER.info(info_id+"");
            KeyboardRow row = new KeyboardRow();// Creando una fila de teclado
            row.add(x.getDescription());
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);
        LOGGER.info("findAllDescriptiontransportByInfo.........................");
        return keyboardMarkup;
    }

/////////////////////////////////////////////////////
    // funcion para devolver la descripcion de los atributos de la tabla transporte por id_info
    public  List<String> findAllDescriptiontransportByInfo(int info_id) {

        LOGGER.info("findAllDescriptiontransportByInfo.........................");
        List<String> ret = this.transportRepository.findAllDescriptiontransportByInfoAndStatus(info_id, Status.ACTIVE.getStatus());
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("findAllDescriptiontransportByInfo null");
            throw new RuntimeException("There is no transport with active status and transpor_info ID:" + info_id);
        }
    }

    // funcion para devolver el url de un transporte segun su descripcion (nombre)
    public String findURLTransportByName(String name) {
        LOGGER.info("findURLTransportByName.........................");
        String ret = this.transportRepository.findRouteImagetransportByDescription(name);
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("findURLTransportByName null");
            throw new RuntimeException("There is no transport with name:" + name);
        }
    }



    //Funciones de TransportInfo

    // funcion para retornar un TransportInfoEntity del transportInfo segun su ID
    public TransportInfoEntity findTransportInfoById(int id) {
        LOGGER.info("findTransportInfoById.........................");
        TransportInfoEntity transportInfoEntity = this.transportInfoRepository.findByIdTransportInfo(id);
        if (transportInfoEntity!=null) {
            return transportInfoEntity;
        } else {
            LOGGER.info("findTransportInfoById null");
            throw new RuntimeException("Record cannot found for TransportInfoEntity with ID: " + id);
        }
    }

    // funcion para retornar TransportInfoEntity del transportInfo
    public  List<TransportInfoEntity> findAlltransportInfo() {
        LOGGER.info("findAlltransportInfo.........................");
        List<TransportInfoEntity> ret = this.transportInfoRepository.findAll();
        if (ret!=null) {
            return ret;
        } else {
            LOGGER.info("findAlltransportInfo null");
            throw new RuntimeException("Record cannot found for TransportInfoEntity");
        }
    }

    // funcion para retornar la descripcion del transportInfo
    public  List<String> findAllDescriptiontransportInfo() {
        LOGGER.info("findAllDescriptiontransportInfo.........................");
        List<String> ret = this.transportInfoRepository.findAllDescriptiontransportInfo();
        if (ret!=null) {
            return ret;
        } else {
            LOGGER.info("findAllDescriptiontransportInfo null");
            throw new RuntimeException("Record cannot found for TransportInfoEntity");
        }
    }

    // funcion para retornar el id del transportInfo segun la descripcion (nombre0
    public int findIdTransportInfoByName(String name) {
        int ret= this.transportInfoRepository.findIdTransportInfoByName(name);
        return ret;
    }
}