package com.example.demo.dto;

import java.time.LocalDateTime;

public class RecomendacionDTO {

    private Long idRecomendacion;
    private CultivoDTO cultivo;
    private LocalDateTime fecha;
    private String recomendacion;

    public RecomendacionDTO() {
    }

    public Long getIdRecomendacion() {
        return idRecomendacion;
    }

    public void setIdRecomendacion(Long idRecomendacion) {
        this.idRecomendacion = idRecomendacion;
    }

    public CultivoDTO getCultivo() {
        return cultivo;
    }

    public void setCultivo(CultivoDTO cultivo) {
        this.cultivo = cultivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }
}
