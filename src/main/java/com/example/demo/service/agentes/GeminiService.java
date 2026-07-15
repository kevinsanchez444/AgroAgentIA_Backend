package com.example.demo.service.agentes;

import com.example.demo.dto.gemini.Candidate;
import com.example.demo.dto.gemini.Contenido;
import com.example.demo.dto.gemini.GeminiRequest;
import com.example.demo.dto.gemini.GeminiResponse;
import com.example.demo.dto.gemini.Parte;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generarRespuesta(String prompt) {

        try {

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="
                            + apiKey;

            Parte parte = new Parte(prompt);
            Contenido contenido = new Contenido(List.of(parte));
            GeminiRequest request = new GeminiRequest(List.of(contenido));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<GeminiRequest> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<GeminiResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            GeminiResponse.class);

            GeminiResponse body = response.getBody();

            if (body == null
                    || body.getCandidates() == null
                    || body.getCandidates().isEmpty()
                    || body.getCandidates().get(0).getContent() == null
                    || body.getCandidates().get(0).getContent().getParts() == null
                    || body.getCandidates().get(0).getContent().getParts().isEmpty()) {

                return "No fue posible generar una recomendación porque Gemini respondió sin contenido.";
            }

            return body.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        } catch (HttpClientErrorException e) {

            // Muestra el error completo en la consola
            System.err.println("Error HTTP de Gemini:");
            System.err.println(e.getResponseBodyAsString());

            if (e.getStatusCode().value() == 429) {
                return """
                        No fue posible generar la recomendación porque
                        la cuota gratuita de la API de Gemini está agotada.
                        
                        Intente nuevamente más tarde o utilice una API Key con cuota disponible.
                        """;
            }

            if (e.getStatusCode().value() == 401) {
                return "La API Key de Gemini es inválida o ha expirado.";
            }

            if (e.getStatusCode().value() == 404) {
                return "El modelo de Gemini solicitado no existe o no está disponible.";
            }

            return "Error al consumir la API de Gemini. Código HTTP: " + e.getStatusCode();

        } catch (ResourceAccessException e) {

            System.err.println("Error de conexión con Gemini:");
            e.printStackTrace();

            return "No fue posible establecer conexión con la API de Gemini.";

        } catch (Exception e) {

            System.err.println("Error inesperado en Gemini:");
            e.printStackTrace();

            return "Ocurrió un error inesperado al generar la recomendación.";
        }
    }
}