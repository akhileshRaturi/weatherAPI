package com.learning.weatherAPI.service;

import com.learning.weatherAPI.entity.Weather;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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

    private RedisTemplate<String, Weather> redisTemplate;

    public WeatherService(RedisTemplate<String,Weather> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public Weather getWeather(String place){
        String url = apiUrl + place + "?unitGroup=metric&key=" + apiKey;
        //logger.info("url Called : {}", url);
        Map<String, Object> map = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> days = (List<Map<String, Object>>) map.getOrDefault("days", new ArrayList<>());

        List<Weather> weatherDetails = new ArrayList<>();

        String country = (String)map.get("address");

        for(Map<String, Object> d : days){

            String temperature = String.valueOf(d.get("temp"));
            String description = (String) d.get("description");
            String dateTime = (String) d.get("datetime");

            Weather currentInfo = new Weather(country, temperature, description, dateTime);

            weatherDetails.add(currentInfo);
        }
        saveWeather(country, weatherDetails.get(0));

        return weatherDetails.get(0);
    }

    public void saveWeather(String key, Weather value){
        redisTemplate.opsForValue().set(key, value);
    }

    public Weather getWeatherRedis(String country){
        return redisTemplate.opsForValue().get(country);
    }

    /*public List<Weather> getCacheDetails(){
        Set<String> keys = redisTemplate.keys("*");

        logger.info("keys = {}",keys);
        if(keys!= null)
        {
            List<Weather> cache = keys.stream()
                    .map(e -> redisTemplate.opsForValue().get(e))
                    .collect(Collectors.toList());
            return cache;
        }
        return null;
    }*/
}
