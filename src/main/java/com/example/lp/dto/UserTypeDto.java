package com.example.lp.dto;

import com.example.lp.domain.UserTypeEntity;

import java.sql.Date;

public class UserTypeDto {
    private int idUserType;
    private String type;
    private String token;
    private String txUser;
    private String txHost;
    private Date txDate;

    public UserTypeDto(String type, String token, String txUser, String txHost, Date txDate) {
        this.type = type;
        this.token = token;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }
    public UserTypeDto(UserTypeEntity userTypeEntity) {
        this.type = userTypeEntity.getType();
        this.token = userTypeEntity.getToken();
        this.txUser = userTypeEntity.getTxUser();
        this.txHost = userTypeEntity.getTxHost();
        this.txDate = userTypeEntity.getTxDate();
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }
}
