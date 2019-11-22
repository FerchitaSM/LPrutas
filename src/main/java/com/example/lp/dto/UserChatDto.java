package com.example.lp.dto;

import com.example.lp.domain.UserChatEntity;

import java.sql.Date;

public class UserChatDto {
    private int idUserChat;
    private int idUser;
    private String inMessage;
    private String outMessage;
    private Date msgDate;
    private String txUser;
    private String txHost;
    private Date txDate;


    public UserChatDto(int idUser, String inMessage, String outMessage, Date msgDate, String txUser, String txHost, Date txDate) {
        this.idUser = idUser;
        this.inMessage = inMessage;
        this.outMessage = outMessage;
        this.msgDate = msgDate;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public UserChatDto(UserChatEntity userChatEntity) {
        this.idUserChat = userChatEntity.getIdUserChat();
        this.idUser = userChatEntity.getIdUser();
        this.inMessage = userChatEntity.getInMessage();
        this.outMessage = userChatEntity.getOutMessage();
        this.msgDate = userChatEntity.getMsgDate();
        this.txUser = userChatEntity.getTxUser();
        this.txHost = userChatEntity.getTxHost();
        this.txDate = userChatEntity.getTxDate();
    }


    public int getIdUserChat() {
        return idUserChat;
    }

    public void setIdUserChat(int idUserChat) {
        this.idUserChat = idUserChat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
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
