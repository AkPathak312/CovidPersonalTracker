package com.example.covidpersonaltracker;

public class CovidDataModel {
    private int id;
    private String temp;
    private String datetime;
    private String location;

    public CovidDataModel(int id,String temp, String datetime, String location) {
        this.id=id;
        this.temp = temp;
        this.datetime = datetime;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
