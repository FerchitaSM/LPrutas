package com.example.lp.dto;

import com.example.lp.domain.UserInfoEntity;

public class UserInfoDto {

    private Integer id_user;
    private String first_name;
    private String last_name;
    public UserInfoDto(){

    }

    public UserInfoDto(UserInfoEntity userInfoEntity){
        this.first_name = userInfoEntity.getFirstName();
        this.last_name = userInfoEntity.getLastName();
    }

    public Integer getId_user(){return id_user;}

    public void setId_user(Integer id_user) {this.id_user = id_user;}

    public String getFirst_name() {return first_name;}

    public void setFirst_name(String first_name){this.first_name = first_name;}

    public String getLast_name() {return last_name;}

    public void setLast_name(String last_name) {this.last_name = last_name;}
}
