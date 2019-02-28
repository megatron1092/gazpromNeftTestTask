package ru.gazprom_neft.testTask.service;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import ru.gazprom_neft.testTask.model.Cities;
import ru.gazprom_neft.testTask.service.util.Validator;

import java.io.IOException;
import java.io.StringWriter;

@Service
public class WeatherService implements WeatherInterface {

    private static final String API_KEY = "c2367185-ba97-460a-a17c-aee4fc50cc35";
    private static final String YANDEX_WEATHER_URL = "https://api.weather.yandex.ru/v1/forecast";
    private static final String URL_POSTFIX = "?lat=%s&lon=%s&limit=%s";


    @Override
    public String getWeatherFromYandexByCity(String city, String daysToForecast) throws Exception {
        String sWeather;
        if (!Validator.checkCity(city)) {
            throw new Exception(new StringBuilder().append("Specified city ").append(city).append(" is not implemented").toString());
        }

        String latitude = Cities.getByName(city).getLatitude();
        String longitude = Cities.getByName(city).getLongitude();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String postfix = String.format(URL_POSTFIX, latitude, longitude, daysToForecast);
        HttpGet httpGet = new HttpGet(YANDEX_WEATHER_URL + postfix);
        httpGet.setHeader("X-Yandex-API-Key", API_KEY);
        sWeather = getDataFromURL(httpclient, httpGet);

        return sWeather;
    }

    @Override
    public String getWeatherFromYandexByCoordinates(String latitude, String longitude, String daysToForecast) throws Exception {
        String sWeather;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        String postfix = String.format(URL_POSTFIX, latitude, longitude, daysToForecast);
        HttpGet httpGet = new HttpGet(YANDEX_WEATHER_URL + postfix);
        httpGet.setHeader("X-Yandex-API-Key", API_KEY);
        sWeather = getDataFromURL(httpclient, httpGet);

        return sWeather;
    }

    private String getDataFromURL(CloseableHttpClient httpclient, HttpGet httpGet) throws IOException {
        String result;
        try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            HttpEntity entity1 = response1.getEntity();
            StringWriter writer = new StringWriter();
            IOUtils.copy(entity1.getContent(), writer, "UTF-8");

            result = writer.toString();
            EntityUtils.consume(entity1);
        }
        return result;
    }
}
