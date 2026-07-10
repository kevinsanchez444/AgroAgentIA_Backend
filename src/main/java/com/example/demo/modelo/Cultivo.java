package com.example.demo.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cultivos")
public class Cultivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCultivo;
    
    private String nombreLote;
    private String municipio;
    private String departamento;
    private BigDecimal hectareas;
    private Integer cantidadArboles;
    private LocalDate fechaSiembra;
    private String variedad;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "cultivo", cascade = CascadeType.ALL)
    private List<Recomendacion> recomendaciones;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Recomendacion> getRecomendaciones() {
		return recomendaciones;
	}

	public void setRecomendaciones(List<Recomendacion> recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
    
    
}
