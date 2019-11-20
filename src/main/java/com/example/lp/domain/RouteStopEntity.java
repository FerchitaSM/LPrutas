package com.example.lp.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@Entity
@Table(name = "route_stop", schema = "dbtest_lpbus2", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RouteStopEntity.findRoute", query = "SELECT c FROM RouteStopEntity c WHERE c.stopIdStop = :stopIdStop"),
        @NamedQuery(name = "RouteStopEntity.findRouteFinish", query = "SELECT c FROM RouteStopEntity c WHERE c.routeIdRoute = :routeIdRoute and c.stopIdStop = :stopIdStop")
})
public class RouteStopEntity {
    private int idRouteStop;
    private int routeIdRoute;
    private int stopIdStop;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "id_route_stop")
    public int getIdRouteStop() {
        return idRouteStop;
    }

    public void setIdRouteStop(int idRouteStop) {
        this.idRouteStop = idRouteStop;
    }

    @Basic
    @Column(name = "route_id_route")
    public int getRouteIdRoute() {
        return routeIdRoute;
    }

    public void setRouteIdRoute(int routeIdRoute) {
        this.routeIdRoute = routeIdRoute;
    }

    @Basic
    @Column(name = "stop_id_stop")
    public int getStopIdStop() {
        return stopIdStop;
    }

    public void setStopIdStop(int stopIdStop) {
        this.stopIdStop = stopIdStop;
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
    @Column(name = "tx_host")
    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
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

        RouteStopEntity that = (RouteStopEntity) o;

        if (idRouteStop != that.idRouteStop) return false;
        if (routeIdRoute != that.routeIdRoute) return false;
        if (stopIdStop != that.stopIdStop) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRouteStop;
        result = 31 * result + routeIdRoute;
        result = 31 * result + stopIdStop;
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }
}
