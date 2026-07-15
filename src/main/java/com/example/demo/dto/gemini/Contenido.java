package com.example.demo.dto.gemini;

import java.util.List;

public class Contenido {

    private List<Parte> parts;

    public Contenido() {
    }

    public Contenido(List<Parte> parts) {
        this.parts = parts;
    }

    public List<Parte> getParts() {
        return parts;
    }

    public void setParts(List<Parte> parts) {
        this.parts = parts;
    }

}