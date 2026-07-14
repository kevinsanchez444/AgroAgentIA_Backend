package com.example.demo.dto;

public class ClimaDTO {
private Double temperatura;
    private Integer humedad;
    private String descripcion;

    public ClimaDTO() {
    }

    public ClimaDTO(Double temperatura, Integer humedad, String descripcion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.descripcion = descripcion;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getHumedad() {
        return humedad;
    }

    public void setHumedad(Integer humedad) {
        this.humedad = humedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
