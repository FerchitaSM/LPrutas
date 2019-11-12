package com.example.lp.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ruta", schema = "lapazbuses", catalog = "")
public class RutaEntity {
    private int idRuta;
    private Integer status;
    private String textUser;
    private String textHost;
    private int idOrigen;
    private int idDestino;
    private Collection<MovilidadRutaEntity> movilidadRutasByIdRuta;
    private ParadaEntity paradaByIdOrigen;
    private ParadaEntity paradaByIdDestino;

    @Id
    @Column(name = "id_ruta", nullable = false)
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
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
    @Column(name = "id_origen", nullable = false)
    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    @Basic
    @Column(name = "id_destino", nullable = false)
    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutaEntity that = (RutaEntity) o;
        return idRuta == that.idRuta &&
                idOrigen == that.idOrigen &&
                idDestino == that.idDestino &&
                Objects.equals(status, that.status) &&
                Objects.equals(textUser, that.textUser) &&
                Objects.equals(textHost, that.textHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRuta, status, textUser, textHost, idOrigen, idDestino);
    }

    @OneToMany(mappedBy = "rutaByIdRuta")
    public Collection<MovilidadRutaEntity> getMovilidadRutasByIdRuta() {
        return movilidadRutasByIdRuta;
    }

    public void setMovilidadRutasByIdRuta(Collection<MovilidadRutaEntity> movilidadRutasByIdRuta) {
        this.movilidadRutasByIdRuta = movilidadRutasByIdRuta;
    }

    @ManyToOne
    @JoinColumn(name = "id_origen", referencedColumnName = "id_parada", nullable = false)
    public ParadaEntity getParadaByIdOrigen() {
        return paradaByIdOrigen;
    }

    public void setParadaByIdOrigen(ParadaEntity paradaByIdOrigen) {
        this.paradaByIdOrigen = paradaByIdOrigen;
    }

    @ManyToOne
    @JoinColumn(name = "id_destino", referencedColumnName = "id_parada", nullable = false)
    public ParadaEntity getParadaByIdDestino() {
        return paradaByIdDestino;
    }

    public void setParadaByIdDestino(ParadaEntity paradaByIdDestino) {
        this.paradaByIdDestino = paradaByIdDestino;
    }
}
