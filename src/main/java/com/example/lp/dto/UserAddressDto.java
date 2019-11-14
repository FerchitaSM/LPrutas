package com.example.lp.dto;

import com.example.lp.domain.UserAddressEntity;

public class UserAddressDto {
    private Integer id_user_address;
    private double latitute;
    private double longitud;

    public UserAddressDto() {
    }

    public UserAddressDto(UserAddressEntity userAddressEntity) {
        this.id_user_address = userAddressEntity.getIdUserAddress();
        this.latitute = userAddressEntity.getLatitude();
        this.longitud = userAddressEntity.getLongitude();
    }

    public Integer getId_user_address() {
        return id_user_address;
    }

    public void setId_user_address(Integer id_user_address) {
        this.id_user_address = id_user_address;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
