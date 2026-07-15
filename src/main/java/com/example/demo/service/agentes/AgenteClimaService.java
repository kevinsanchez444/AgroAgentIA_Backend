package com.example.demo.service.agentes;

import com.example.demo.dto.RespuestaClimaDTO;
import com.example.demo.dto.weather.OpenWeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgenteClimaService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    public AgenteClimaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RespuestaClimaDTO obtenerClima(String ciudad) {

        String url =
                "https://api.openweathermap.org/data/2.5/weather?q="
                        + ciudad
                        + "&appid="
                        + apiKey
                        + "&units=metric&lang=es";

        OpenWeatherResponse respuesta =
                restTemplate.getForObject(url, OpenWeatherResponse.class);

        RespuestaClimaDTO clima = new RespuestaClimaDTO();

        clima.setTemperatura(respuesta.getMain().getTemp());
        clima.setHumedad(respuesta.getMain().getHumidity());
        clima.setDescripcion(
                respuesta.getWeather().get(0).getDescription());

        return clima;
    }

}
