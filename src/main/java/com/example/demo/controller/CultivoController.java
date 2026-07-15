package com.example.demo.controller;

import com.example.demo.dto.CultivoDTO;
import com.example.demo.service.CultivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cultivos")
public class CultivoController {

    private final CultivoService cultivoService;

    @Autowired
    public CultivoController(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerCultivos(@RequestParam(required = false) Long id) {
        if (id != null) {
            CultivoDTO cultivo = cultivoService.obtenerPorId(id);
            return ResponseEntity.ok(cultivo);
        }
        List<CultivoDTO> cultivos = cultivoService.obtenerTodos();
        return ResponseEntity.ok(cultivos);
    }

    @PostMapping
    public ResponseEntity<CultivoDTO> crearCultivo(@RequestBody CultivoDTO dto) {
        CultivoDTO creado = cultivoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping
    public ResponseEntity<CultivoDTO> actualizarCultivo(
            @RequestParam(required = false) Long id,
            @RequestBody CultivoDTO dto) {
        Long idToUse = id != null ? id : dto.getIdCultivo();
        if (idToUse == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El ID del cultivo es requerido como parámetro (?id=...) o dentro del cuerpo JSON (idCultivo)");
        }
        CultivoDTO actualizado = cultivoService.actualizar(idToUse, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarCultivo(@RequestParam Long id) {
        cultivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
