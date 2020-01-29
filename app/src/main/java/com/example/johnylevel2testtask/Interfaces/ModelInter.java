package com.example.johnylevel2testtask.Interfaces;
import com.example.johnylevel2testtask.WeatherObject.WeatherDay;

public interface ModelInter {
    WeatherDay getWeatherDay();
    void setWeatherDay(WeatherDay day);
    void getWeatherDayFromServer();
}
