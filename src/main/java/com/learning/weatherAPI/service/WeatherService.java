package com.learning.weatherAPI.service;

import com.learning.weatherAPI.entity.Weather;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Anky
 * The below service calls the external api using the API key and return the weather details if found else throws error.
 */


@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Resource
    private RestTemplate restTemplate;

    public Weather getWeather(String place){
        String url = apiUrl + place + "?unitGroup=metric&key=" + apiKey;
        //logger.info("url Called : {}", url);
        Map<String, Object> map = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> days = (List<Map<String, Object>>) map.get("days");

        List<Weather> weatherDetails = new ArrayList<>();

       // logger.info("Details : {}", map);

        String country = (String)map.get("address");

        for(Map<String, Object> d : days){

            String temperature = String.valueOf(d.get("temp"));
            String description = (String) d.get("description");
            String dateTime = (String) d.get("datetime");

            Weather currentInfo = new Weather(country, temperature, description, dateTime);

            weatherDetails.add(currentInfo);
        }

        return weatherDetails.get(0);
    }
}
