package com.example.johnylevel2testtask.Interfaces;

import android.content.Context;

public interface ViewInter {

    void setTextTemperature(String text);
    void setTextHumidity(String text);
    void setTextPressure(String text);
    void setTextWind_speed(String text);
    void ProgressBarSetVisibleTRUE();
    void  ProgressBarSetVisibleFALSE();
    void setIconWeather(String text);
    Context getContex();



}
