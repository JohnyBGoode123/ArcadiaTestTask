package com.example.johnylevel2testtask.MVP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.johnylevel2testtask.Common.StorageWeather;
import com.example.johnylevel2testtask.Interfaces.ViewInter;
import com.example.johnylevel2testtask.R;

public class MainActivity extends AppCompatActivity implements ViewInter {

    Model model;
    Presenter presenter;
    TextView textTemperature;
    TextView iconview;
    TextView textHumidity;
    TextView textWind_speed;
    TextView textPressure;
    TextView lack_of_network;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(!checkConnection())
        {
            lack_of_network.setText(this.getResources().getString(R.string.lack_of_network));
                 setTextTemperature(StorageWeather.getProperty("Weather_Temp"));
                setTextPressure(StorageWeather.getProperty("Weather_Pressure"));
                setTextHumidity(StorageWeather.getProperty("Weather_Humidity"));
                setTextWind_speed(StorageWeather.getProperty("Weather_WindSpeed"));
                setIconWeather(StorageWeather.getProperty("Weather_Icon"));

        }
        else
        {
            if(savedInstanceState==null)
            presenter.requestToServer();
        }

    }

    public void init()
    {

        textTemperature = findViewById(R.id.temperature);
        textHumidity = findViewById(R.id.value_humidity);
        textWind_speed = findViewById(R.id.value_wind_speed);
        textPressure = findViewById(R.id.value_pressure);
        lack_of_network = findViewById(R.id.lack_of_network);

        iconview = findViewById(R.id.weather_icon);
        progressBar = findViewById(R.id.progress_bar);

        model = new Model();
        presenter = new Presenter(this,model);
        model.attachInter(presenter);
        StorageWeather.init(this);
    }

    public void setTextTemperature(String text) {
        textTemperature.setText(text + "\u2103");
    }

    @Override
    public void setTextHumidity(String text) {
        textHumidity.setText(text + " %");
    }

    @Override
    public void setTextPressure(String text) {
        textPressure.setText(text + " гПа");
    }

    @Override
    public void setTextWind_speed(String text) {
        textWind_speed.setText(text + " м/с");
    }

    public void ProgressBarSetVisibleTRUE()
    {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }
    public void ProgressBarSetVisibleFALSE  ()
    {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void setIconWeather(String text) {
        iconview.setText(text);
    }

    @Override
    public Context getContex() {
        return getApplicationContext();
    }


    public boolean checkConnection() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityInfo = manager.getActiveNetworkInfo();
        if(null==activityInfo)
        {
            return false;

        }
        return true;
    }

        @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("temperature", textTemperature.getText().toString());
        outState.putString("pressure", textPressure.getText().toString());
        outState.putString("humidity", textHumidity.getText().toString());
        outState.putString("windspeed", textWind_speed.getText().toString());
        outState.putString("icon", iconview.getText().toString());

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textTemperature.setText(savedInstanceState.getString("temperature"));
        textHumidity.setText(savedInstanceState.getString("humidity"));
        textPressure.setText(savedInstanceState.getString("pressure"));
        textWind_speed.setText(savedInstanceState.getString("windspeed"));
        iconview.setText(savedInstanceState.getString("icon"));

    }



}
