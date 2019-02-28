package ru.gazprom_neft.testTask.model;
// Created by IntelliJ IDEA.
// User: Sergey Telitsyn
// Date: 05.09.2018

import java.util.HashSet;
import java.util.Set;

public enum Cities {

    MOSCOW("Moscow", "55.4521", "37.3704"),
    ST_PETERSBURG("St.Petersburg", "59.57", "30.19"),
    NEW_YORK("New-York", "40.4342", "73.5939");


    private String name;
    private String latitude;
    private String longitude;

    Cities(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Cities getByName(String name){
        for (Cities city : Cities.values()){
            if (city.name.equalsIgnoreCase(name)){
                return city;
            }
        }
        return null;
    }


    public static Set<String> getPossibleValues(){
        HashSet<String> set = new HashSet<>();
        set.add(MOSCOW.name.toLowerCase());
        set.add(ST_PETERSBURG.name.toLowerCase());
        set.add(NEW_YORK.name.toLowerCase());
        return set;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
