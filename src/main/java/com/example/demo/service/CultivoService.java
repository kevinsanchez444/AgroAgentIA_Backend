package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Cultivo;
import com.example.demo.repository.CultivoRepository;

@Service
public class CultivoService {

    @Autowired
    private CultivoRepository cultivoRepository;

    public Cultivo guardar(Cultivo cultivo) {
        return cultivoRepository.save(cultivo);
    }

    public List<Cultivo> listar() {
        return cultivoRepository.findAll();
    }

}
