package com.example.demo.modelo;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecomendacion;
    
    @ManyToOne
    @JoinColumn(name = "cultivo_id")
    private Cultivo cultivo;
    
    private LocalDateTime fecha;
    
    @Column(columnDefinition = "TEXT")
    private String recomendacion;

	public Long getIdRecomendacion() {
		return idRecomendacion;
	}

	public void setIdRecomendacion(Long idRecomendacion) {
		this.idRecomendacion = idRecomendacion;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
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