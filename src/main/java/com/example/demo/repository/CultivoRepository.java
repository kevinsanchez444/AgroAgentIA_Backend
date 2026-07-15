package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelo.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Long> {

    // Total de árboles registrados
    @Query("SELECT COALESCE(SUM(c.cantidadArboles),0) FROM Cultivo c")
    Long totalArboles();

    // Cantidad de municipios diferentes
    @Query("SELECT COUNT(DISTINCT c.municipio) FROM Cultivo c")
    Long totalMunicipios();

    // Último cultivo registrado
    Cultivo findTopByOrderByIdCultivoDesc();

}