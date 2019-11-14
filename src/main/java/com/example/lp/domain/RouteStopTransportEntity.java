package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "route_stop_transport", schema = "dbtest_lpbus2", catalog = "")
public class RouteStopTransportEntity {
    private int idRouteStopTransport;
    private String txHost;
    private String txUser;
    private Date txDate;

    @Id
    @Column(name = "id_route_stop_transport")
    public int getIdRouteStopTransport() {
        return idRouteStopTransport;
    }

    public void setIdRouteStopTransport(int idRouteStopTransport) {
        this.idRouteStopTransport = idRouteStopTransport;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteStopTransportEntity that = (RouteStopTransportEntity) o;

        if (idRouteStopTransport != that.idRouteStopTransport) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRouteStopTransport;
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }
}
