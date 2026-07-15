package com.example.demo.service;

import com.example.demo.dto.CultivoDTO;
import com.example.demo.dto.RecomendacionDTO;
import com.example.demo.modelo.Cultivo;
import com.example.demo.modelo.Recomendacion;
import com.example.demo.repository.RecomendacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionService {

    private final RecomendacionRepository recomendacionRepository;

    public RecomendacionService(RecomendacionRepository recomendacionRepository) {
        this.recomendacionRepository = recomendacionRepository;
    }

    @Transactional(readOnly = true)
    public List<RecomendacionDTO> obtenerTodas() {
        return recomendacionRepository.findAllByOrderByFechaDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RecomendacionDTO convertToDTO(Recomendacion recomendacion) {
        RecomendacionDTO dto = new RecomendacionDTO();
        dto.setIdRecomendacion(recomendacion.getIdRecomendacion());
        dto.setFecha(recomendacion.getFecha());
        dto.setRecomendacion(recomendacion.getRecomendacion());
        dto.setCultivo(convertCultivoToDTO(recomendacion.getCultivo()));
        return dto;
    }

    private CultivoDTO convertCultivoToDTO(Cultivo cultivo) {
        if (cultivo == null) {
            return null;
        }

        CultivoDTO dto = new CultivoDTO();
        dto.setIdCultivo(cultivo.getIdCultivo());
        dto.setNombreLote(cultivo.getNombreLote());
        dto.setMunicipio(cultivo.getMunicipio());
        dto.setDepartamento(cultivo.getDepartamento());
        dto.setHectareas(cultivo.getHectareas());
        dto.setCantidadArboles(cultivo.getCantidadArboles());
        dto.setFechaSiembra(cultivo.getFechaSiembra());
        dto.setVariedad(cultivo.getVariedad());
        dto.setEstado(cultivo.getEstado());
        if (cultivo.getUsuario() != null) {
            dto.setIdUsuario(cultivo.getUsuario().getIdUsuario());
        }
        return dto;
    }
}
