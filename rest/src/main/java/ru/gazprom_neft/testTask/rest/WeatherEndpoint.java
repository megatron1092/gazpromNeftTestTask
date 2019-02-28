package ru.gazprom_neft.testTask.rest;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gazprom_neft.testTask.service.WeatherInterface;
import ru.gazprom_neft.testTask.util.RestValidator;

@RestController
@RequestMapping("/v1")
public class WeatherEndpoint {

    private static String DEFAULT_DAYS_TO_FORECAST = "1";

    final
    WeatherInterface weather;

    @Autowired
    public WeatherEndpoint(WeatherInterface weather) {
        this.weather = weather;
    }

    @RequestMapping(value = "/weather")
    public ResponseEntity getWeather(@RequestParam(name = "city", required = false) String city,
                                     @RequestParam(name = "daysToForecast", required = false) String daysToForecast,
                                     final @RequestParam(name = "latitude", required = false) String latitude,
                                     final @RequestParam(name = "longitude", required = false) String longitude) {

        String weatherResult;
        if (StringUtils.isEmpty(daysToForecast)) {
            daysToForecast = DEFAULT_DAYS_TO_FORECAST;
        }
        try {
            RestValidator.validateRequest(city, latitude, longitude);
            if (!StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(longitude)) {
                weatherResult = weather.getWeatherFromYandexByCoordinates(latitude, longitude, daysToForecast);
            } else {
                city = city.toUpperCase();
                weatherResult = weather.getWeatherFromYandexByCity(city, daysToForecast);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON_UTF8).body("{\"ERROR\" : "+"\"Error in weather obtaining - "
                    + ex.getLocalizedMessage()+"\"}");
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(weatherResult);
    }
}
