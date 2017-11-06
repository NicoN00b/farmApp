package com.epicodus.pdxfarmshare.models;


public class Weather {
    private String city;
    private String temp;
    private String description;

    public Weather (){}

    public Weather (String city, String temp, String description) {
        this.city = city;
        this.temp = temp;
        this.description = description;
    }

    public String getCity() {
        return city;
    }


    public String getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }
}
