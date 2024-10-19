package com.learning.weatherAPI.controller;

import com.learning.weatherAPI.entity.Weather;
import com.learning.weatherAPI.service.WeatherService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anky
 * This is the Rest controller for getting weather from external API and Redis.
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService=weatherService;
    }

    @GetMapping("/forecast/{country}")
    public ResponseEntity<Weather> getForecast(@PathVariable String country){
        Weather weatherDetails =  weatherService.getWeatherRedis(country);
        if(weatherDetails!= null){
            logger.info("Fetched details from Redis..");
            return new ResponseEntity<>(weatherDetails, HttpStatus.OK);
        }

        logger.info("Fetching weather details from API");
        weatherDetails = weatherService.getWeather(country);

        if(weatherDetails != null)
        {
            return new ResponseEntity<>(weatherDetails, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("/cache")
    public ResponseEntity<List<Weather>> getCache(){
        List<Weather> cache = weatherService.getCacheDetails();
        if (cache!= null)
            return new ResponseEntity<>(cache, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

}
