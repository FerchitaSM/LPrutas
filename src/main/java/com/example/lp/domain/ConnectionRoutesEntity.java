package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "connection_routes", schema = "lpbus_bot", catalog = "")
public class ConnectionRoutesEntity {
    private int idCoroutes;
    private Integer coroutesStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private int routeA;
    private int routeB;
    private int typeConnection;
    private String description;

    @Id
    @Column(name = "id_coroutes")
    public int getIdCoroutes() {
        return idCoroutes;
    }

    public void setIdCoroutes(int idCoroutes) {
        this.idCoroutes = idCoroutes;
    }

    @Basic
    @Column(name = "coroutes_status")
    public Integer getCoroutesStatus() {
        return coroutesStatus;
    }

    public void setCoroutesStatus(Integer coroutesStatus) {
        this.coroutesStatus = coroutesStatus;
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
    @Column(name = "route_a")
    public int getRouteA() {
        return routeA;
    }

    public void setRouteA(int routeA) {
        this.routeA = routeA;
    }

    @Basic
    @Column(name = "route_b")
    public int getRouteB() {
        return routeB;
    }

    public void setRouteB(int routeB) {
        this.routeB = routeB;
    }

    @Basic
    @Column(name = "type_connection")
    public int getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(int typeConnection) {
        this.typeConnection = typeConnection;
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

        ConnectionRoutesEntity that = (ConnectionRoutesEntity) o;

        if (idCoroutes != that.idCoroutes) return false;
        if (coroutesStatus != null ? !coroutesStatus.equals(that.coroutesStatus) : that.coroutesStatus != null)
            return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (routeA != that.routeA) return false;
        if (routeB != that.routeB) return false;
        if (typeConnection != that.typeConnection) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCoroutes;
        result = 31 * result + (coroutesStatus != null ? coroutesStatus.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + routeA;
        result = 31 * result + routeB;
        result = 31 * result + typeConnection;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
