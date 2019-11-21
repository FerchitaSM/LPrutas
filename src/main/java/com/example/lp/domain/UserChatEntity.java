package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_chat", schema = "lpbus_bot", catalog = "")
public class UserChatEntity {
    private int idUserChat;
    private int idUser;
    private String inMessage;
    private String outMessage;
    private Date msgDate;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "id_user_chat", nullable = false)
    public int getIdUserChat() {
        return idUserChat;
    }

    public void setIdUserChat(int idUserChat) {
        this.idUserChat = idUserChat;
    }

    @Basic
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "in_message", nullable = false, length = 400)
    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    @Basic
    @Column(name = "out_message", nullable = true, length = 400)
    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    @Basic
    @Column(name = "msg_date", nullable = false)
    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    @Basic
    @Column(name = "tx_user", nullable = false, length = 50)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_host", nullable = false, length = 100)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_date", nullable = false)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserChatEntity that = (UserChatEntity) o;

        if (idUserChat != that.idUserChat) return false;
        if (idUser != that.idUser) return false;
        if (inMessage != null ? !inMessage.equals(that.inMessage) : that.inMessage != null) return false;
        if (outMessage != null ? !outMessage.equals(that.outMessage) : that.outMessage != null) return false;
        if (msgDate != null ? !msgDate.equals(that.msgDate) : that.msgDate != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserChat;
        result = 31 * result + idUser;
        result = 31 * result + (inMessage != null ? inMessage.hashCode() : 0);
        result = 31 * result + (outMessage != null ? outMessage.hashCode() : 0);
        result = 31 * result + (msgDate != null ? msgDate.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }
}
