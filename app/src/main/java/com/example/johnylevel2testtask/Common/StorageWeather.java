package com.example.johnylevel2testtask.Common;
import android.content.Context;
import android.content.SharedPreferences;

public class StorageWeather {
    public static final String Weather_Day = "0";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init(Context cntxt ){
        context = cntxt;
        settings = context.getSharedPreferences(Weather_Day, Context.MODE_PRIVATE);
        editor = settings.edit();

    }
    public static void addProperty( String name, String value ){

        editor.putString( name, value );
        editor.apply();
    }
    public static String getProperty( String name ){

        return settings.getString( name, "0" );
    }


}
