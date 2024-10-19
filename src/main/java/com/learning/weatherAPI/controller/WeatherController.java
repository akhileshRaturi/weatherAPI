package com.learning.weatherAPI.controller;

import com.learning.weatherAPI.entity.Weather;
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

    @GetMapping("/forecast/{country}")
    public ResponseEntity<Weather> getForecast(@PathVariable String country){

    }
}
