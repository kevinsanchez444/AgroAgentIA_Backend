package com.example.demo.service.agentes;

import com.example.demo.dto.RespuestaClimaDTO;
import com.example.demo.dto.RespuestaRecomendacionDTO;
import com.example.demo.dto.SolicitudRecomendacionDTO;
import com.example.demo.modelo.Cultivo;
import com.example.demo.modelo.Recomendacion;
import com.example.demo.repository.CultivoRepository;
import com.example.demo.repository.RecomendacionRepository;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AgenteIAService {

    private final AgenteClimaService agenteClimaService;
    private final GeminiService geminiService;
    private final CultivoRepository cultivoRepository;
    private final RecomendacionRepository recomendacionRepository;

    public AgenteIAService(AgenteClimaService agenteClimaService,
                    GeminiService geminiService,
                    CultivoRepository cultivoRepository,
                    RecomendacionRepository recomendacionRepository) {
        this.agenteClimaService = agenteClimaService;
        this.geminiService = geminiService;
        this.cultivoRepository = cultivoRepository;
        this.recomendacionRepository = recomendacionRepository;
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

    @Transactional
    public RespuestaRecomendacionDTO generarYGuardarRecomendacion(Long idCultivo,
            SolicitudRecomendacionDTO solicitud) {

        Cultivo cultivo = cultivoRepository.findById(idCultivo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cultivo no encontrado con ID: " + idCultivo));

        RespuestaRecomendacionDTO respuesta = generarRecomendacion(solicitud);

        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setCultivo(cultivo);
        recomendacion.setFecha(LocalDateTime.now());
        recomendacion.setRecomendacion(respuesta.getRecomendacion());

        recomendacionRepository.save(recomendacion);

        return respuesta;
    }
}
