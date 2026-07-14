package com.example.demo.service.agentes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgenteClimaService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String obtenerClima(String ciudad) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + ciudad
                + "&appid="
                + apiKey
                + "&units=metric&lang=es";

        return restTemplate.getForObject(url, String.class);
    }


}
