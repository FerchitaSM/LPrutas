package com.example.lp.dto;

import com.example.lp.domain.MovilidadEntity;

public class MovilidadDto {
    private int idMovilidad;
    private Integer status;
    private String textUser;
    private String textHost;
    private int idTipoMovilidad;

    public MovilidadDto() {
    }
    public MovilidadDto(MovilidadEntity movilidadEntity) {
        this.idMovilidad = movilidadEntity.getIdMovilidad();
        this.status = movilidadEntity.getStatus();
        this.textUser = movilidadEntity.getTextUser();
        this.textHost = movilidadEntity.getTextHost();
        this.idTipoMovilidad = movilidadEntity.getIdTipoMovilidad();
    }

    public MovilidadDto(int idMovilidad, Integer status, String textUser, String textHost, int idTipoMovilidad) {
        this.idMovilidad = idMovilidad;
        this.status = status;
        this.textUser = textUser;
        this.textHost = textHost;
        this.idTipoMovilidad = idTipoMovilidad;
    }

    public int getIdMovilidad() {
        return idMovilidad;
    }

    public void setIdMovilidad(int idMovilidad) {
        this.idMovilidad = idMovilidad;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTextUser() {
        return textUser;
    }

    public void setTextUser(String textUser) {
        this.textUser = textUser;
    }

    public String getTextHost() {
        return textHost;
    }

    public void setTextHost(String textHost) {
        this.textHost = textHost;
    }

    public int getIdTipoMovilidad() {
        return idTipoMovilidad;
    }

    public void setIdTipoMovilidad(int idTipoMovilidad) {
        this.idTipoMovilidad = idTipoMovilidad;
    }
}
