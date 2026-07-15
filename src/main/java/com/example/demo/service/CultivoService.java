package com.example.demo.service;

import com.example.demo.dto.CultivoDTO;
import com.example.demo.modelo.Cultivo;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.CultivoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CultivoService {

    private final CultivoRepository cultivoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CultivoService(CultivoRepository cultivoRepository, UsuarioRepository usuarioRepository) {
        this.cultivoRepository = cultivoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<CultivoDTO> obtenerTodos() {
        return cultivoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CultivoDTO obtenerPorId(Long id) {
        Cultivo cultivo = cultivoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Cultivo no encontrado con ID: " + id));
        return convertToDTO(cultivo);
    }

    @Transactional
    public CultivoDTO crear(CultivoDTO dto) {
        Cultivo cultivo = new Cultivo();
        updateEntityFromDTO(cultivo, dto);
        Cultivo guardado = cultivoRepository.save(cultivo);
        return convertToDTO(guardado);
    }

    @Transactional
    public CultivoDTO actualizar(Long id, CultivoDTO dto) {
        Cultivo cultivoExistente = cultivoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Cultivo no encontrado para actualizar con ID: " + id));
        updateEntityFromDTO(cultivoExistente, dto);
        Cultivo actualizado = cultivoRepository.save(cultivoExistente);
        return convertToDTO(actualizado);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!cultivoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Cultivo no encontrado para eliminar con ID: " + id);
        }
        cultivoRepository.deleteById(id);
    }

    private CultivoDTO convertToDTO(Cultivo cultivo) {
        if (cultivo == null) return null;
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

    private void updateEntityFromDTO(Cultivo cultivo, CultivoDTO dto) {
        cultivo.setNombreLote(dto.getNombreLote());
        cultivo.setMunicipio(dto.getMunicipio());
        cultivo.setDepartamento(dto.getDepartamento());
        cultivo.setHectareas(dto.getHectareas());
        cultivo.setCantidadArboles(dto.getCantidadArboles());
        cultivo.setFechaSiembra(dto.getFechaSiembra());
        cultivo.setVariedad(dto.getVariedad());
        cultivo.setEstado(dto.getEstado());
        
        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                            "Usuario no encontrado con ID: " + dto.getIdUsuario()));
            cultivo.setUsuario(usuario);
        } else {
            cultivo.setUsuario(null);
        }
    }
}
