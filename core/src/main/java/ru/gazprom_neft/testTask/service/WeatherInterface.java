package ru.gazprom_neft.testTask.service;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018

public interface WeatherInterface {

    String getWeatherFromYandexByCity(String city, String daysToForecast) throws Exception;
    String getWeatherFromYandexByCoordinates(String latitude, String longitude, String daysToForecast) throws Exception;
}
