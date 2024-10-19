package com.learning.weatherAPI.controller;

import com.learning.weatherAPI.entity.Weather;
import com.learning.weatherAPI.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anky
 * This is the Rest controller for getting weather from external API and Redis.
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping("/forecast/{country}")
    public ResponseEntity<Weather> getForecast(@PathVariable String country){
        Weather weatherDetails = weatherService.getWeather(country);
        if(weatherDetails!= null){
            return new ResponseEntity<>(weatherDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
