package com.example.johnylevel2testtask.MVP;

import com.example.johnylevel2testtask.API_OpenWeatherMap.API_OpenWeatherMap;
import com.example.johnylevel2testtask.Common.Constants;
import com.example.johnylevel2testtask.Interfaces.ModelInter;
import com.example.johnylevel2testtask.Interfaces.PresenterInter;
import com.example.johnylevel2testtask.WeatherObject.WeatherDay;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements ModelInter {

    PresenterInter presenterInter;
    WeatherDay day;
    Call<WeatherDay> call;

    public void attachInter(PresenterInter presenter) {
        this.presenterInter = presenter;
    }

    @Override
    public WeatherDay getWeatherDay() {
        return day;
    }

    @Override
    public void setWeatherDay(WeatherDay day) {
    this.day = day;
    }

    @Override
    public void getWeatherDayFromServer() {

        API_OpenWeatherMap.ApiInterface apiInterface = API_OpenWeatherMap.getRetrofit().create(API_OpenWeatherMap.ApiInterface.class);
        call =  apiInterface.getToday(Constants.Latitude, Constants.Longitude, Constants.Metric, Constants.Key);
        call.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                setWeatherDay(response.body());
                presenterInter.sendDatatoView();
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {

            }
        });

    }

}
