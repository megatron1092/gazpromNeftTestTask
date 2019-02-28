package ru.gazprom_neft.testTask.service.util;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018

import ru.gazprom_neft.testTask.model.Cities;

public class Validator {

    public static Boolean checkCity(String cityName){
        return Cities.getPossibleValues().contains(cityName.toLowerCase());
    }
}
