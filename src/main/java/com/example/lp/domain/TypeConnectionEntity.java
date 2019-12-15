package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "type_connection", schema = "lpbus_bot", catalog = "")
@NamedQueries({
        @NamedQuery(name = "TypeConnectionEntity.findByt_transport", query = "SELECT a FROM TypeConnectionEntity a WHERE a.tTransportA = :tTransportA and a.tTransportB =:tTransportB"),
        @NamedQuery(name = "TypeConnectionEntity.findByCompare_and", query = "SELECT a FROM ConnectionRoutesEntity a,TypeConnectionEntity c WHERE c.tTransportA = :tTransportA and c.tTransportB =:tTransportB and c.idTypeconnection=a.typeConnection"),
        @NamedQuery(name = "TypeConnectionEntity.findByCompare_or", query = "SELECT a FROM ConnectionRoutesEntity a,TypeConnectionEntity c WHERE c.tTransportA = :tTransportA or c.tTransportA = :tTransportB or c.tTransportB =:tTransportB or c.tTransportB =:tTransportA and c.idTypeconnection=a.typeConnection")

})

public class TypeConnectionEntity {
    private int idTypeconnection;
    private Integer typeconnectionStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private int tTransportA;
    private int tTransportB;
    private String description;

    @Id
    @Column(name = "id_typeconnection")
    public int getIdTypeconnection() {
        return idTypeconnection;
    }

    public void setIdTypeconnection(int idTypeconnection) {
        this.idTypeconnection = idTypeconnection;
    }

    @Basic
    @Column(name = "typeconnection_status")
    public Integer getTypeconnectionStatus() {
        return typeconnectionStatus;
    }

    public void setTypeconnectionStatus(Integer typeconnectionStatus) {
        this.typeconnectionStatus = typeconnectionStatus;
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

    @Basic
    @Column(name = "t_transport_a")
    public int gettTransportA() {
        return tTransportA;
    }

    public void settTransportA(int tTransportA) {
        this.tTransportA = tTransportA;
    }

    @Basic
    @Column(name = "t_transport_b")
    public int gettTransportB() {
        return tTransportB;
    }

    public void settTransportB(int tTransportB) {
        this.tTransportB = tTransportB;
    }



    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeConnectionEntity that = (TypeConnectionEntity) o;

        if (idTypeconnection != that.idTypeconnection) return false;
        if (typeconnectionStatus != null ? !typeconnectionStatus.equals(that.typeconnectionStatus) : that.typeconnectionStatus != null)
            return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (tTransportA != that.tTransportA) return false;
        if (tTransportB != that.tTransportB) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeconnection;
        result = 31 * result + (typeconnectionStatus != null ? typeconnectionStatus.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + tTransportA;
        result = 31 * result + tTransportB;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
