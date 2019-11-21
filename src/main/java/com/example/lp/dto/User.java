package com.example.lp.dto;

import com.example.lp.domain.UsersEntity;

import javax.persistence.Id;
import java.sql.Date;

public class User {
    private int idUser;
    private int idUserBot;
    private int uStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private String userName;

    public User(int idUserBot, int uStatus, String txHost, String txUser, Date txDate, String userName) {
        this.idUserBot = idUserBot;
        this.uStatus = uStatus;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.userName = userName;
    }

    public User(int idUserBot, String userName) {
        this.idUserBot = idUserBot;
        this.userName = userName;
    }

    public User(UsersEntity usersEntity){
        this.idUserBot = usersEntity.getIdUserBot();
        this.uStatus = usersEntity.getuStatus();
        this.txHost = usersEntity.getTxHost();
        this.txUser = usersEntity.getTxUser();
        this.txDate = usersEntity.getTxDate();
        this.userName = usersEntity.getUserName();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserBot() {
        return idUserBot;
    }

    public void setIdUserBot(int idUserBot) {
        this.idUserBot = idUserBot;
    }

    public int getuStatus() {
        return uStatus;
    }

    public void setuStatus(int uStatus) {
        this.uStatus = uStatus;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
