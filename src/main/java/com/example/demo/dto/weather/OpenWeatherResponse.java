package com.example.demo.dto.weather;

import java.util.List;
public class OpenWeatherResponse {
private MainWeather main;
    private List<WeatherInfo> weather;

    public MainWeather getMain() {
        return main;
    }

    public void setMain(MainWeather main) {
        this.main = main;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }
}
