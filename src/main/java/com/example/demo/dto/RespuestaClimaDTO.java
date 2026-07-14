package com.example.demo.dto;

public class RespuestaClimaDTO {
     private double temperatura;
    private int humedad;
    private String descripcion;

    public RespuestaClimaDTO() {
    }

    public RespuestaClimaDTO(double temperatura, int humedad, String descripcion) {
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.descripcion = descripcion;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
