package com.example.lp.domain;

import javax.persistence.*;

@Entity
@Table(name = "exception", schema = "lpbus_bot", catalog = "")
public class ExceptionEntity {
    private int idException;
    private String questionMessage;
    private String answerMessage;

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
}
