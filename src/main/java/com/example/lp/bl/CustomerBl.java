package com.example.lp.bl;

import com.example.lp.dao.UserInfoRepository;
import com.example.lp.domain.UserInfoEntity;
import com.example.lp.dto.Status;
import com.example.lp.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerBl {

    @Autowired
    UserInfoRepository userInfoRepository;


    public CustomerBl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfoEntity findPersonById(int pk) {
        Optional<UserInfoEntity> optional = this.userInfoRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // Otra alternativa podría ser: crear una nueva persona con valores por defecto y retornar este nuevo objeto
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }

    public List<UserInfoDto> findAllUsers() {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        for (UserInfoEntity userInfoEntity:userInfoRepository.findAllByUserInfoStatus(Status.ACTIVE.getStatus())) {
            userInfoDtoList.add(new UserInfoDto(userInfoEntity));
        }
        return userInfoDtoList;
    }

    /*
    public List<UserInfoDto> findAllPeopleWithAddress(Integer pk) {
        List<UserInfoDto> userInfoDtoList = new ArrayList<>();
        for (UserInfoEntity userInfoEntity:userInfoRepository.findAllByIdUser(pk)) {
            UserInfoDto userInfoDto = new UserInfoDto(userInfoEntity);
            List<UserAddressDto> userAddressDtoList = new ArrayList<>();
            List<UserAddressEntity> userAddressEntityList = userInfoEntity.getCpPersonAddressList();
            for(UserAddressEntity cpa : userAddressEntityList) {
                UserAddressEntity address = cpa.getIdUserAddress();
                userAddressDtoList.add(new UserAddressDto(address));
            }
            userInfoDto.setAddressList(userAddressDtoList);
            userInfoDto.add(userInfoDto);
        }
        return personDtoList;
    }
    /*

    public void saveOrder(UserTelegram user,  List<BookDto> books) {
        // mi logica de negocio
        // books -> CpBooks(Etities)

    }

     */
}
