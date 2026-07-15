package com.example.demo.controller;

import com.example.demo.dto.RespuestaClimaDTO;
import com.example.demo.dto.RespuestaRecomendacionDTO;
import com.example.demo.dto.SolicitudRecomendacionDTO;
import com.example.demo.service.agentes.AgenteClimaService;
import com.example.demo.service.agentes.AgenteIAService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agente")
public class AgenteController {

    private final AgenteClimaService agenteClimaService;
    private final AgenteIAService agenteIAService;

    public AgenteController(AgenteClimaService agenteClimaService,
                            AgenteIAService agenteIAService) {

        this.agenteClimaService = agenteClimaService;
        this.agenteIAService = agenteIAService;
    }

    @GetMapping("/clima")
    public RespuestaClimaDTO obtenerClima(@RequestParam String ciudad) {

        return agenteClimaService.obtenerClima(ciudad);

    }

    @PostMapping("/recomendacion")
    public RespuestaRecomendacionDTO generarRecomendacion(
            @RequestBody SolicitudRecomendacionDTO solicitud) {

        return agenteIAService.generarRecomendacion(solicitud);

    }

}
