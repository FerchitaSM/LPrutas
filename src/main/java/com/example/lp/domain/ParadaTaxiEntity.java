package com.example.lp.domain;

import javax.persistence.*;

@Entity
@Table(name = "parada_taxi", schema = "lpbus_bot", catalog = "")
public class ParadaTaxiEntity {
    private int idTaxi;
    private String companyName;
    private String phoneNumber;
    private String zone;
    private String address;
    private String mapPhoto;

    @Id
    @Column(name = "id_taxi")
    public int getIdTaxi() {
        return idTaxi;
    }

    public void setIdTaxi(int idTaxi) {
        this.idTaxi = idTaxi;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "zone")
    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "map_photo")
    public String getMapPhoto() {
        return mapPhoto;
    }

    public void setMapPhoto(String mapPhoto) {
        this.mapPhoto = mapPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParadaTaxiEntity that = (ParadaTaxiEntity) o;

        if (idTaxi != that.idTaxi) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (mapPhoto != null ? !mapPhoto.equals(that.mapPhoto) : that.mapPhoto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTaxi;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mapPhoto != null ? mapPhoto.hashCode() : 0);
        return result;
    }
}
