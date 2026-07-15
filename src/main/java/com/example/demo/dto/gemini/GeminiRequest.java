package com.example.demo.dto.gemini;

import java.util.List;

public class GeminiRequest {

    private List<Contenido> contents;

    public GeminiRequest() {
    }

    public GeminiRequest(List<Contenido> contents) {
        this.contents = contents;
    }

    public List<Contenido> getContents() {
        return contents;
    }

    public void setContents(List<Contenido> contents) {
        this.contents = contents;
    }

}
