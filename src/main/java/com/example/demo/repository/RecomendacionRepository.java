package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modelo.Recomendacion;
import java.util.List;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, Long> {

    List<Recomendacion> findAllByOrderByFechaDesc();

}
