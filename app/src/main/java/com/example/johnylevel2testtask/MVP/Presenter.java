package com.example.johnylevel2testtask.MVP;

import com.example.johnylevel2testtask.Common.StorageWeather;
import com.example.johnylevel2testtask.Interfaces.ModelInter;
import com.example.johnylevel2testtask.Interfaces.PresenterInter;
import com.example.johnylevel2testtask.Interfaces.ViewInter;
import com.example.johnylevel2testtask.R;
import com.example.johnylevel2testtask.WeatherObject.WeatherDay;

import java.util.Date;


public class Presenter implements PresenterInter {
    ViewInter view;
    ModelInter model;
    WeatherDay weatherDay;

    public Presenter(ViewInter view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void requestToServer()
    {
        view.ProgressBarSetVisibleTRUE();
        model.getWeatherDayFromServer();
    }

    @Override
    public void sendDatatoView() {

        weatherDay = model.getWeatherDay();
        String tempTemperature = Double.toString(weatherDay.getMain().getTemp()) ;
        view.setTextTemperature(tempTemperature);
        StorageWeather.addProperty("Weather_Temp",tempTemperature);

        String tempPressure = Double.toString(weatherDay.getMain().getPressure()) ;
        view.setTextPressure(tempPressure);
        StorageWeather.addProperty("Weather_Pressure",tempPressure);

        String tempHumidity = Double.toString(weatherDay.getMain().getHumidity()) ;
        view.setTextHumidity(tempHumidity);
        StorageWeather.addProperty("Weather_Humidity",tempHumidity);

        String tempWindSpeed = Double.toString(weatherDay.getWind().getSpeed()) ;
        view.setTextWind_speed(tempWindSpeed);
        StorageWeather.addProperty("Weather_WindSpeed",tempWindSpeed);




        setWeatherIcon(weatherDay.getCod(),weatherDay.getSys().getSunrise(),weatherDay.getSys().getSunset());


        view.ProgressBarSetVisibleFALSE();
    }
           private void setWeatherIcon(int actualId, long sunrise, long sunset){
            int id = actualId / 100;
            String icon = "";
            if(actualId == 800){
                long currentTime = new Date().getTime();
                if(currentTime>=sunrise && currentTime<sunset) {
                    icon = view.getContex().getString(R.string.weather_sunny);
                } else {
                    icon = view.getContex().getString(R.string.weather_clear_night);
                }
            } else {

                switch(id) {
                    case 2 : icon = view.getContex().getString(R.string.weather_thunder);
                        break;
                    case 3 : icon = view.getContex().getString(R.string.weather_drizzle);
                        break;
                    case 7 : icon = view.getContex().getString(R.string.weather_foggy);
                        break;
                    case 8 : icon = view.getContex().getString(R.string.weather_cloudy);
                        break;
                    case 6 : icon = view.getContex().getString(R.string.weather_snowy);
                        break;
                    case 5 : icon = view.getContex().getString(R.string.weather_rainy);
                        break;
                }
            }
               StorageWeather.addProperty("Weather_Icon",icon);
               view.setIconWeather(icon);
    }


}
