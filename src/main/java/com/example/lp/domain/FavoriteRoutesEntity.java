package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "favorite_routes", schema = "lpbus_bot", catalog = "")
public class FavoriteRoutesEntity {
    private int idFavoriteRoutes;
    private String name;
    private String txUser;
    private String txHost;
    private Date txDate;

    @Id
    @Column(name = "id_favorite_routes")
    public int getIdFavoriteRoutes() {
        return idFavoriteRoutes;
    }

    public void setIdFavoriteRoutes(int idFavoriteRoutes) {
        this.idFavoriteRoutes = idFavoriteRoutes;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        FavoriteRoutesEntity that = (FavoriteRoutesEntity) o;

        if (idFavoriteRoutes != that.idFavoriteRoutes) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFavoriteRoutes;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }
}
