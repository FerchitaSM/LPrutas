package com.example.lp.bl;


import com.example.lp.dao.TransportInfoRepository;
import com.example.lp.domain.TransportInfoEntity;
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
public class TransportInfoBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportBl.class);

    private TransportInfoRepository transportInfoRepository;

    @Autowired
    public TransportInfoBl( TransportInfoRepository transportInfoRepository) {
        this.transportInfoRepository = transportInfoRepository;
    }

    /*public TransportInfoEntity findTransportInfoById(int id) {
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
    }*/

  /*  public ReplyKeyboardMarkup options(Update update){
        List<String> listasTransportInfo=new ArrayList<>();
        for(int i =0 ; i<listasTransportInfo.size(); i++)
        {
            if (listasTransportInfo.get(i).equals(getCall_data()))
            {
                int id= transportInfoBl.findIdTransportInfoByName(listasTransportInfo.get(i));
                sacar_TransportePorInfo(id);
                break;
            }
        }

    }*/
  // sacando la informacion de transportes
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





///////////////////////////////////////////////////////////////////
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
