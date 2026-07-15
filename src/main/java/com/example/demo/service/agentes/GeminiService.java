package com.example.demo.service.agentes;

import com.example.demo.dto.gemini.Contenido;
import com.example.demo.dto.gemini.GeminiRequest;
import com.example.demo.dto.gemini.GeminiResponse;
import com.example.demo.dto.gemini.Parte;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key="
                    + apiKey;

            Parte parte = new Parte(prompt);
            Contenido contenido = new Contenido(List.of(parte));
            GeminiRequest request = new GeminiRequest(List.of(contenido));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<GeminiRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<GeminiResponse> response = restTemplate.exchange(
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

                return """
                        Gemini respondió correctamente,
                        pero no generó contenido.
                        """;
            }

            return body.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        }

        catch (HttpClientErrorException e) {

            System.err.println(e.getResponseBodyAsString());

            switch (e.getStatusCode().value()) {

                case 401:
                    return "❌ La API Key de Gemini es inválida.";

                case 404:
                    return "❌ El modelo de Gemini no existe.";

                case 429:
                    return """
                            ⚠️ Se alcanzó el límite gratuito de Gemini.

                            Intente nuevamente más tarde.
                            """;

                default:
                    return "❌ Error HTTP " + e.getStatusCode();
            }

        }

        catch (HttpServerErrorException e) {

            System.err.println(e.getResponseBodyAsString());

            if (e.getStatusCode().value() == 503) {

                return """
                        ⚠️ Gemini se encuentra temporalmente ocupado.

                        Esto ocurre cuando el servicio tiene alta demanda.

                        Intente nuevamente en unos minutos.
                        """;
            }

            return "Error del servidor de Gemini: " + e.getStatusCode();

        }

        catch (ResourceAccessException e) {

            return """
                    ❌ No fue posible conectar con Gemini.

                    Revise su conexión a Internet.
                    """;

        }

        catch (Exception e) {

            e.printStackTrace();

            return """
                    ❌ Ocurrió un error inesperado al generar
                    la recomendación.
                    """;
        }

    }

}