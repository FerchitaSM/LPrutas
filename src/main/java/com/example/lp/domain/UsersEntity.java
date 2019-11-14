package com.example.lp.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@javax.persistence.Table(name = "users", schema = "dbtest_lpbus2", catalog = "")
public class UsersEntity {
    private int idUser;

    @Id
    @javax.persistence.Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private int uStatus;

    @Basic
    @javax.persistence.Column(name = "u_status")
    public int getuStatus() {
        return uStatus;
    }

    public void setuStatus(int uStatus) {
        this.uStatus = uStatus;
    }

    private String txHost;

    @Basic
    @javax.persistence.Column(name = "tx_host")
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    private String txUser;

    @Basic
    @javax.persistence.Column(name = "tx_user")
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    private Date txDate;

    @Basic
    @javax.persistence.Column(name = "tx_date")
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    private String userName;

    @Basic
    @javax.persistence.Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String lastChat;

    @Basic
    @javax.persistence.Column(name = "last_chat")
    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (idUser != that.idUser) return false;
        if (uStatus != that.uStatus) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (lastChat != null ? !lastChat.equals(that.lastChat) : that.lastChat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + uStatus;
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (lastChat != null ? lastChat.hashCode() : 0);
        return result;
    }
}
