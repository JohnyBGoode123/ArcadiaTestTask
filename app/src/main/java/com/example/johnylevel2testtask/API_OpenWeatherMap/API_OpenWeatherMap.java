package com.example.johnylevel2testtask.API_OpenWeatherMap;

import com.example.johnylevel2testtask.Common.Constants;
import com.example.johnylevel2testtask.WeatherObject.WeatherDay;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class API_OpenWeatherMap {
    private static Retrofit retrofit = null;

    public interface ApiInterface {
        @GET("weather")
        Call<WeatherDay> getToday(
                @Query("lat") Double lat,
                @Query("lon") Double lon,
                @Query("units") String units,
                @Query("appid") String appid
        );


    }
    public static Retrofit getRetrofit()
    {
            if(retrofit==null)
            {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constants.Base_Url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
             return retrofit;



    }
}
