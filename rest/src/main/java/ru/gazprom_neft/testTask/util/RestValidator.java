package ru.gazprom_neft.testTask.util;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018


import static org.apache.commons.lang3.StringUtils.isEmpty;

public class RestValidator {
    public static void validateRequest(String city,
                                       String latitude,
                                       String longitude) throws Exception {

        if (isEmpty(city) && (isEmpty(latitude) || isEmpty(longitude))) {
            throw new Exception("City or both latitude and longitude must be entered");
        }


    }
}
