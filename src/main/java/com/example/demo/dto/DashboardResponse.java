package com.example.demo.dto;

public class DashboardResponse {

    private Long cultivos;
    private Long recomendaciones;
    private Long totalArboles;
    private Long municipios;

    private String ultimoLote;
    private String ultimoEstado;

    public DashboardResponse() {
    }

    public Long getCultivos() {
        return cultivos;
    }

    public void setCultivos(Long cultivos) {
        this.cultivos = cultivos;
    }

    public Long getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(Long recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Long getTotalArboles() {
        return totalArboles;
    }

    public void setTotalArboles(Long totalArboles) {
        this.totalArboles = totalArboles;
    }

    public Long getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Long municipios) {
        this.municipios = municipios;
    }

    public String getUltimoLote() {
        return ultimoLote;
    }

    public void setUltimoLote(String ultimoLote) {
        this.ultimoLote = ultimoLote;
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }

}