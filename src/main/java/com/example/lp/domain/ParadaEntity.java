package com.example.lp.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "parada", schema = "lapazbuses", catalog = "")
public class ParadaEntity {
    private int idParada;
    private Integer status;
    private String textUser;
    private String textHost;
    private String nameParada;
    private Double latitud;
    private Double longitud;
    private Collection<RutaEntity> rutasByIdParada;
    private Collection<RutaEntity> rutasByIdParada_0;

    @Id
    @Column(name = "id_parada", nullable = false)
    public int getIdParada() {
        return idParada;
    }

    public void setIdParada(int idParada) {
        this.idParada = idParada;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "text_user", nullable = true, length = 200)
    public String getTextUser() {
        return textUser;
    }

    public void setTextUser(String textUser) {
        this.textUser = textUser;
    }

    @Basic
    @Column(name = "text_host", nullable = true, length = 200)
    public String getTextHost() {
        return textHost;
    }

    public void setTextHost(String textHost) {
        this.textHost = textHost;
    }

    @Basic
    @Column(name = "name_parada", nullable = true, length = 200)
    public String getNameParada() {
        return nameParada;
    }

    public void setNameParada(String nameParada) {
        this.nameParada = nameParada;
    }

    @Basic
    @Column(name = "latitud", nullable = true, precision = 0)
    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Basic
    @Column(name = "longitud", nullable = true, precision = 0)
    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParadaEntity that = (ParadaEntity) o;
        return idParada == that.idParada &&
                Objects.equals(status, that.status) &&
                Objects.equals(textUser, that.textUser) &&
                Objects.equals(textHost, that.textHost) &&
                Objects.equals(nameParada, that.nameParada) &&
                Objects.equals(latitud, that.latitud) &&
                Objects.equals(longitud, that.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParada, status, textUser, textHost, nameParada, latitud, longitud);
    }

    @OneToMany(mappedBy = "paradaByIdOrigen")
    public Collection<RutaEntity> getRutasByIdParada() {
        return rutasByIdParada;
    }

    public void setRutasByIdParada(Collection<RutaEntity> rutasByIdParada) {
        this.rutasByIdParada = rutasByIdParada;
    }

    @OneToMany(mappedBy = "paradaByIdDestino")
    public Collection<RutaEntity> getRutasByIdParada_0() {
        return rutasByIdParada_0;
    }

    public void setRutasByIdParada_0(Collection<RutaEntity> rutasByIdParada_0) {
        this.rutasByIdParada_0 = rutasByIdParada_0;
    }
}
