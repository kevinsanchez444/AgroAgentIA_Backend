package com.example.demo.repository;

import com.example.demo.modelo.Cultivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {
}
