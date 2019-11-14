package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transport_info", schema = "dbtest_lpbus2", catalog = "")
public class TransportInfoEntity {
    private int idTransportInfo;
    private String txHosts;
    private String txUser;
    private Date txDate;
    private int type;
    private String infoDescription;

    @Id
    @Column(name = "id_transport_info")
    public int getIdTransportInfo() {
        return idTransportInfo;
    }

    public void setIdTransportInfo(int idTransportInfo) {
        this.idTransportInfo = idTransportInfo;
    }

    @Basic
    @Column(name = "tx_hosts")
    public String getTxHosts() {
        return txHosts;
    }

    public void setTxHosts(String txHosts) {
        this.txHosts = txHosts;
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

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "info_description")
    public String getInfoDescription() {
        return infoDescription;
    }

    public void setInfoDescription(String infoDescription) {
        this.infoDescription = infoDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportInfoEntity that = (TransportInfoEntity) o;

        if (idTransportInfo != that.idTransportInfo) return false;
        if (type != that.type) return false;
        if (txHosts != null ? !txHosts.equals(that.txHosts) : that.txHosts != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (infoDescription != null ? !infoDescription.equals(that.infoDescription) : that.infoDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransportInfo;
        result = 31 * result + (txHosts != null ? txHosts.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (infoDescription != null ? infoDescription.hashCode() : 0);
        return result;
    }
}
