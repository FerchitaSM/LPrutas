package com.example.lp.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Entity
@Table(name = "transport", schema = "lpbus_bot", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TransportEntity.findTransport", query = "SELECT a FROM TransportEntity a WHERE a.transportInfoIdTransportInfo = :transportInfoIdTransportInfo"),
})
public class TransportEntity {
    private int idTransport;
    private int transportStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private String description;
    private int transportInfoIdTransportInfo;
    private String routeImage;

    @Id
    @Column(name = "id_transport", nullable = false)
    public int getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    @Basic
    @Column(name = "transport_status", nullable = false)
    public int getTransportStatus() {
        return transportStatus;
    }

    public void setTransportStatus(int transportStatus) {
        this.transportStatus = transportStatus;
    }

    @Basic
    @Column(name = "tx_host", nullable = false, length = 200)
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    @Basic
    @Column(name = "tx_user", nullable = false, length = 200)
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_date", nullable = false)
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "transport_info_id_transport_info", nullable = false)
    public int getTransportInfoIdTransportInfo() {
        return transportInfoIdTransportInfo;
    }

    public void setTransportInfoIdTransportInfo(int transportInfoIdTransportInfo) {
        this.transportInfoIdTransportInfo = transportInfoIdTransportInfo;
    }

    @Basic
    @Column(name = "route_image", nullable = false, length = -1)
    public String getRouteImage() {
        return routeImage;
    }

    public void setRouteImage(String routeImage) {
        this.routeImage = routeImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportEntity that = (TransportEntity) o;

        if (idTransport != that.idTransport) return false;
        if (transportStatus != that.transportStatus) return false;
        if (transportInfoIdTransportInfo != that.transportInfoIdTransportInfo) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (routeImage != null ? !routeImage.equals(that.routeImage) : that.routeImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTransport;
        result = 31 * result + transportStatus;
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + transportInfoIdTransportInfo;
        result = 31 * result + (routeImage != null ? routeImage.hashCode() : 0);
        return result;
    }
}
