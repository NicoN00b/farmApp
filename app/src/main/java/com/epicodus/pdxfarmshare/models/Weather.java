package com.epicodus.pdxfarmshare.models;


public class Weather {
    private String city;
    private Double temp;
    private String description;

    public Weather (){}

    public Weather (String city, Double temp, String description) {
        this.city = city;
        this.temp = temp;
        this.description = description;
    }

    public String getCity() {
        return city;
    }


    public Double getTemp() {
        return temp;
    }

    public String getDescription() {
        return description;
    }
}
