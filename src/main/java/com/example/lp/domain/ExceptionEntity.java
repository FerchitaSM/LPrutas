package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "exception", schema = "lpbus_bot", catalog = "")
public class ExceptionEntity {
    private int idException;
    private String questionMessage;
    private String answerMessage;
    private String txHost;
    private String txUser;
    private Date txDate;

    @Id
    @Column(name = "id_exception", nullable = false)
    public int getIdException() {
        return idException;
    }

    public void setIdException(int idException) {
        this.idException = idException;
    }

    @Basic
    @Column(name = "question_message", nullable = false, length = 400)
    public String getQuestionMessage() {
        return questionMessage;
    }

    public void setQuestionMessage(String questionMessage) {
        this.questionMessage = questionMessage;
    }

    @Basic
    @Column(name = "answer_message", nullable = false, length = 400)
    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExceptionEntity that = (ExceptionEntity) o;

        if (idException != that.idException) return false;
        if (questionMessage != null ? !questionMessage.equals(that.questionMessage) : that.questionMessage != null)
            return false;
        if (answerMessage != null ? !answerMessage.equals(that.answerMessage) : that.answerMessage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idException;
        result = 31 * result + (questionMessage != null ? questionMessage.hashCode() : 0);
        result = 31 * result + (answerMessage != null ? answerMessage.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "tx_host")
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_user")
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_date")
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }
}
