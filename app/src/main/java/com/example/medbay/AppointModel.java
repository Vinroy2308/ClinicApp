package com.example.medbay;

public class AppointModel {
    String name;
    String appDate;
    String appTime;

    public AppointModel(String name, String appDate, String appTime) {
        this.name = name;
        this.appDate = appDate;
        this.appTime = appTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
}
