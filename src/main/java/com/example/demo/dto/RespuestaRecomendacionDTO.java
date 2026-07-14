package com.example.demo.dto;

public class RespuestaRecomendacionDTO {
private String recomendacion;

    public RespuestaRecomendacionDTO() {
    }

    public RespuestaRecomendacionDTO(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
}
