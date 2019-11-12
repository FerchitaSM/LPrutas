package com.example.lp.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "movilidad_ruta", schema = "lapazbuses", catalog = "")
public class MovilidadRutaEntity {
    private int idMovilidadRuta;
    private Integer status;
    private String textUser;
    private String textHost;
    private int idMovilidad;
    private int idRuta;
    private MovilidadEntity movilidadByIdMovilidad;
    private RutaEntity rutaByIdRuta;

    @Id
    @Column(name = "id_movilidad_ruta", nullable = false)
    public int getIdMovilidadRuta() {
        return idMovilidadRuta;
    }

    public void setIdMovilidadRuta(int idMovilidadRuta) {
        this.idMovilidadRuta = idMovilidadRuta;
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
    @Column(name = "id_movilidad", nullable = false)
    public int getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(int idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    @Basic
    @Column(name = "id_ruta", nullable = false)
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovilidadRutaEntity that = (MovilidadRutaEntity) o;
        return idMovilidadRuta == that.idMovilidadRuta &&
                idMovilidad == that.idMovilidad &&
                idRuta == that.idRuta &&
                Objects.equals(status, that.status) &&
                Objects.equals(textUser, that.textUser) &&
                Objects.equals(textHost, that.textHost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovilidadRuta, status, textUser, textHost, idMovilidad, idRuta);
    }

    @ManyToOne
    @JoinColumn(name = "id_movilidad", referencedColumnName = "id_movilidad", nullable = false)
    public MovilidadEntity getMovilidadByIdMovilidad() {
        return movilidadByIdMovilidad;
    }

    public void setMovilidadByIdMovilidad(MovilidadEntity movilidadByIdMovilidad) {
        this.movilidadByIdMovilidad = movilidadByIdMovilidad;
    }

    @ManyToOne
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta", nullable = false)
    public RutaEntity getRutaByIdRuta() {
        return rutaByIdRuta;
    }

    public void setRutaByIdRuta(RutaEntity rutaByIdRuta) {
        this.rutaByIdRuta = rutaByIdRuta;
    }
}
