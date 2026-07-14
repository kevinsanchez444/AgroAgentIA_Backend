package com.example.demo.dto;

public class SolicitudRecomendacionDTO {
private String ciudad;
    private String lote;
    private Double hectareas;
    private Integer edadCultivo;

    public SolicitudRecomendacionDTO() {
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Double getHectareas() {
        return hectareas;
    }

    public void setHectareas(Double hectareas) {
        this.hectareas = hectareas;
    }

    public Integer getEdadCultivo() {
        return edadCultivo;
    }

    public void setEdadCultivo(Integer edadCultivo) {
        this.edadCultivo = edadCultivo;
    }
}
