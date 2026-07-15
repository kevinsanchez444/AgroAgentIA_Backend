package com.example.demo.controller;

import com.example.demo.dto.RecomendacionDTO;
import com.example.demo.service.RecomendacionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService recomendacionService;

    public RecomendacionController(RecomendacionService recomendacionService) {
        this.recomendacionService = recomendacionService;
    }

    @GetMapping
    public List<RecomendacionDTO> obtenerRecomendaciones() {
        return recomendacionService.obtenerTodas();
    }
}
