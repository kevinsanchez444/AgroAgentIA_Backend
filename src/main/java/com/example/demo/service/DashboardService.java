package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.modelo.Cultivo;
import com.example.demo.repository.CultivoRepository;
import com.example.demo.repository.RecomendacionRepository;

@Service
public class DashboardService {

    @Autowired
    private CultivoRepository cultivoRepository;

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    public DashboardResponse obtenerDashboard() {

        DashboardResponse dashboard = new DashboardResponse();

        // Tarjetas principales
        dashboard.setCultivos(cultivoRepository.count());
        dashboard.setRecomendaciones(recomendacionRepository.count());
        dashboard.setTotalArboles(cultivoRepository.totalArboles());
        dashboard.setMunicipios(cultivoRepository.totalMunicipios());

        // Último cultivo registrado
        Cultivo ultimoCultivo = cultivoRepository.findTopByOrderByIdCultivoDesc();

        if (ultimoCultivo != null) {
            dashboard.setUltimoLote(ultimoCultivo.getNombreLote());
            dashboard.setUltimoEstado(ultimoCultivo.getEstado());
        } else {
            dashboard.setUltimoLote("Sin registros");
            dashboard.setUltimoEstado("Sin información");
        }

        return dashboard;
    }

}
