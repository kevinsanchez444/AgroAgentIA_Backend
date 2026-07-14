package com.example.demo.controller;

import com.example.demo.service.agentes.AgenteClimaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agente")
public class AgenteController {
    private final AgenteClimaService agenteClimaService;

    public AgenteController(AgenteClimaService agenteClimaService) {
        this.agenteClimaService = agenteClimaService;
    }

    @GetMapping("/clima")
    public String obtenerClima(@RequestParam String ciudad) {

        return agenteClimaService.obtenerClima(ciudad);

    }
}
