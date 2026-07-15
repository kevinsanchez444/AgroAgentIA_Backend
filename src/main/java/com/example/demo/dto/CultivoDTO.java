package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CultivoDTO {
    private Long idCultivo;
    private String nombreLote;
    private String municipio;
    private String departamento;
    private BigDecimal hectareas;
    private Integer cantidadArboles;
    private LocalDate fechaSiembra;
    private String variedad;
    private String estado;
    private Long idUsuario;

    public CultivoDTO() {
    }

    public CultivoDTO(Long idCultivo, String nombreLote, String municipio, String departamento, 
                      BigDecimal hectareas, Integer cantidadArboles, LocalDate fechaSiembra, 
                      String variedad, String estado, Long idUsuario) {
        this.idCultivo = idCultivo;
        this.nombreLote = nombreLote;
        this.municipio = municipio;
        this.departamento = departamento;
        this.hectareas = hectareas;
        this.cantidadArboles = cantidadArboles;
        this.fechaSiembra = fechaSiembra;
        this.variedad = variedad;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public Long getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(Long idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getNombreLote() {
        return nombreLote;
    }

    public void setNombreLote(String nombreLote) {
        this.nombreLote = nombreLote;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getHectareas() {
        return hectareas;
    }

    public void setHectareas(BigDecimal hectareas) {
        this.hectareas = hectareas;
    }

    public Integer getCantidadArboles() {
        return cantidadArboles;
    }

    public void setCantidadArboles(Integer cantidadArboles) {
        this.cantidadArboles = cantidadArboles;
    }

    public LocalDate getFechaSiembra() {
        return fechaSiembra;
    }

    public void setFechaSiembra(LocalDate fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
