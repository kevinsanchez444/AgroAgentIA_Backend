package com.example.demo.service.agentes;

import com.example.demo.dto.RespuestaClimaDTO;
import com.example.demo.dto.RespuestaRecomendacionDTO;
import com.example.demo.dto.SolicitudRecomendacionDTO;
import org.springframework.stereotype.Service;

@Service
public class AgenteIAService {

    private final AgenteClimaService agenteClimaService;
    private final GeminiService geminiService;

    public AgenteIAService(AgenteClimaService agenteClimaService,
                    GeminiService geminiService) {
        this.agenteClimaService = agenteClimaService;
        this.geminiService = geminiService;
    }

    public RespuestaRecomendacionDTO generarRecomendacion(SolicitudRecomendacionDTO solicitud) {

        // Obtener la información del clima desde el Agente Clima
        RespuestaClimaDTO clima =
                agenteClimaService.obtenerClima(solicitud.getCiudad());

        // Construir el prompt para Gemini
        String prompt = """
                Eres un asistente agrícola experto en cultivos de aguacate Hass.

                Analiza la siguiente información y responde en español.

                Información del cultivo:

                Ciudad: %s
                Lote: %s
                Hectáreas: %.2f
                Edad del cultivo: %d años

                Clima actual:

                Temperatura: %.1f °C
                Humedad: %d %%
                Estado del clima: %s

                Genera una recomendación clara y práctica para el agricultor.

                Debes incluir:

                1. Recomendación sobre riego.
                2. Recomendación sobre fertilización.
                3. Riesgos de enfermedades.
                4. Labores agrícolas sugeridas para hoy.

                Responde únicamente con la recomendación, sin repetir la información de entrada.
                """
                .formatted(
                        solicitud.getCiudad(),
                        solicitud.getLote(),
                        solicitud.getHectareas(),
                        solicitud.getEdadCultivo(),
                        clima.getTemperatura(),
                        clima.getHumedad(),
                        clima.getDescripcion());

        // Enviar el prompt a Gemini
        String respuestaIA = geminiService.generarRespuesta(prompt);

        // Devolver la respuesta al frontend
        return new RespuestaRecomendacionDTO(respuestaIA);
    }
}