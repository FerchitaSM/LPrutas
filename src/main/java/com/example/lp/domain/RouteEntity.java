package com.example.lp.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
@Entity
@Table(name = "route", schema = "dbtest_lpbus2", catalog = "")


public class RouteEntity {
    private int idRoute;
    private int routeStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private String routeName;
    private String routeDetails;
    private int stopStart;
    private int stopFinish;

    @Id
    @Column(name = "id_route")
    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    @Basic
    @Column(name = "route_status")
    public int getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(int routeStatus) {
        this.routeStatus = routeStatus;
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
    @Column(name = "route_name")
    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Basic
    @Column(name = "route_details")
    public String getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(String routeDetails) {
        this.routeDetails = routeDetails;
    }

    @Basic
    @Column(name = "stop_start")
    public int getStopStart() {
        return stopStart;
    }

    public void setStopStart(int stopStart) {
        this.stopStart = stopStart;
    }

    @Basic
    @Column(name = "stop_finish")
    public int getStopFinish() { return stopFinish; }

    public void setStopFinish(int stopFinish) {
        this.stopFinish = stopFinish;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (idRoute != that.idRoute) return false;
        if (routeStatus != that.routeStatus) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        if (routeName != null ? !routeName.equals(that.routeName) : that.routeName != null) return false;
        if (routeDetails != null ? !routeDetails.equals(that.routeDetails) : that.routeDetails != null) return false;
        if (stopStart != that.stopStart) return false;
        if (stopFinish != that.stopFinish) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoute;
        result = 31 * result + routeStatus;
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        result = 31 * result + (routeName != null ? routeName.hashCode() : 0);
        result = 31 * result + (routeDetails != null ? routeDetails.hashCode() : 0);
        result = 31 * result + stopStart;
        result = 31 * result + stopFinish;
        return result;
    }
}
