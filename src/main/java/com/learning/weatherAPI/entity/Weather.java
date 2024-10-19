package com.learning.weatherAPI.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anky
 * This is the entity class for weather.
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    private String country;
    private String temperature;
    private String description;
    private String dateTime;
}
