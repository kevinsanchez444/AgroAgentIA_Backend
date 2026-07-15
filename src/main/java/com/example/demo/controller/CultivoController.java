package com.example.demo.controller;

import com.example.demo.dto.CultivoDTO;
import com.example.demo.service.CultivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelo.Cultivo;
import com.example.demo.service.CultivoService;

@RestController
@RequestMapping("/api/cultivos")
@CrossOrigin(origins = "*")
public class CultivoController {

    @Autowired
    private CultivoService cultivoService;

    @PostMapping
    public Cultivo guardar(@RequestBody Cultivo cultivo) {

        return cultivoService.guardar(cultivo);

    }

    @GetMapping
    public List<Cultivo> listar() {

        return cultivoService.listar();

    }

}
