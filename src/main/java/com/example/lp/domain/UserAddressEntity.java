package com.example.lp.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_address", schema = "dbtest_lpbus2", catalog = "")
public class UserAddressEntity {
    private int idUserAddress;
    private int addressStatus;
    private String txHost;
    private String txUser;
    private Date txDate;
    private double latitude;
    private double longitude;
    private String addressName;
    private int addressDescription;

    @Id
    @Column(name = "id_user_address")
    public int getIdUserAddress() {
        return idUserAddress;
    }

    public void setIdUserAddress(int idUserAddress) {
        this.idUserAddress = idUserAddress;
    }

    @Basic
    @Column(name = "address_status")
    public int getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(int addressStatus) {
        this.addressStatus = addressStatus;
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
    @Column(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "address_name")
    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Basic
    @Column(name = "address_description")
    public int getAddressDescription() {
        return addressDescription;
    }

    public void setAddressDescription(int addressDescription) {
        this.addressDescription = addressDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAddressEntity that = (UserAddressEntity) o;

        if (idUserAddress != that.idUserAddress) return false;
        if (addressStatus != that.addressStatus) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (addressDescription != that.addressDescription) return false;
        if (txHost != null ? !txHost.equals(that.txHost) : that.txHost != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        return addressName != null ? addressName.equals(that.addressName) : that.addressName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idUserAddress;
        result = 31 * result + addressStatus;
        result = 31 * result + (txHost != null ? txHost.hashCode() : 0);
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (addressName != null ? addressName.hashCode() : 0);
        result = 31 * result + addressDescription;
        return result;
    }
}
