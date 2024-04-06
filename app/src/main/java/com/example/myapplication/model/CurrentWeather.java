package com.example.myapplication.model;

import android.util.Log;

import com.example.myapplication.R;

import org.json.*;

import java.util.*;

public class CurrentWeather {
    private static String tag = "CurrentWeather";
    //Object keys
    public static String WEATHER = "weather";
    public static String MAIN = "main";
    public static String DESCRIPTION = "description";
    public static String ICON = "icon";
    public static String TEMP = "temp";
    public static String TEMP_MAX = "temp_max";
    public static String TEMP_MIN = "temp_min";
    public static String WIND = "wind";
    public static String SPEED = "speed";
    public static String VISIBILITY = "visibility";
    // Icon Constance
    private static final String CLEAR_SKY_D = "01d";
    private static final String FEW_CLOUDS_D = "02d";
    private static final String SCATTERED_CLOUDS_D = "03d";
    private static final String BROKEN_CLOUDS_D = "04d";
    private static final String SHOWER_RAIN_D = "09d";
    private static final String RAIN_D = "10d";
    private static final String THUNDERSTORM_D = "11d";
    private static final String SNOW_D = "13d";
    private static final String MIST_D = "50d";
    private static final String CLEAR_SKY_N = "01n";
    private static final String FEW_CLOUDS_N = "02n";
    private static final String SCATTERED_CLOUDS_N = "03n";
    private static final String BROKEN_CLOUDS_N = "04n";
    private static final String SHOWER_RAIN_N = "09n";
    private static final String RAIN_N = "10n";
    private static final String THUNDERSTORM_N = "11n";
    private static final String SNOW_N = "13n";
    private static final String MIST_N = "50n";
    //Data
    public static HashMap<String, String> data = new HashMap<>();
    public static String unit = "C";
    public CurrentWeather() {}
    public static void setUnit(String u){
        if (u.equals("F")) unit = "F";
        else unit = "C";
    }
    public static int getIconSource(){
        try {
            switch (data.get(CurrentWeather.ICON)){
                case CLEAR_SKY_D: return R.mipmap.clear_sky_d_foreground;
                case FEW_CLOUDS_D: return R.mipmap.few_clouds_d_foreground;
                case SCATTERED_CLOUDS_D: return R.mipmap.scattered_clouds_d_foreground;
                case BROKEN_CLOUDS_D: return R.mipmap.broken_clouds_d_foreground;
                case SHOWER_RAIN_D: return R.mipmap.shower_rain_d_foreground;
                case RAIN_D: return R.mipmap.rain_d_foreground;
                case THUNDERSTORM_D: return R.mipmap.thunderstorm_d_foreground;
                case SNOW_D: return R.mipmap.snow_d_foreground;
                case MIST_D: return R.mipmap.mist_d_foreground;
                case CLEAR_SKY_N: return R.mipmap.clear_sky_n_foreground;
                case FEW_CLOUDS_N: return R.mipmap.few_clouds_n_foreground;
                case SCATTERED_CLOUDS_N: return R.mipmap.scattered_clouds_n_foreground;
                case BROKEN_CLOUDS_N: return R.mipmap.broken_clouds_n_foreground;
                case SHOWER_RAIN_N: return R.mipmap.rain_n_foreground;
                case RAIN_N: return R.mipmap.rain_n_foreground;
                case THUNDERSTORM_N: return R.mipmap.thunderstorm_n_foreground;
                case SNOW_N: return R.mipmap.snow_n_foreground;
                case MIST_N: return R.mipmap.mist_n_foreground;
                default: return R.mipmap.clear_sky_d_foreground;
            }
        }catch (Exception e){
            Log.d(tag,"Icon Exception");
            return R.mipmap.few_clouds_d_foreground;
        }
    }
    public static void setData(JSONObject jsonObj) throws org.json.JSONException{
        // Convert to Java Data
        JSONObject weather = jsonObj.getJSONArray(WEATHER).getJSONObject(0);
        JSONObject temp = jsonObj.getJSONObject(MAIN);
        JSONObject wind = jsonObj.getJSONObject(WIND);
        String visibility = jsonObj.getString(VISIBILITY);
        // put JSON weather.main
        data.put(WEATHER , weather.getString(MAIN));
        // put JSON weather.description
        data.put(DESCRIPTION , weather.getString(DESCRIPTION).toUpperCase());
        // put JSON weather.icon
        data.put(ICON, weather.getString(ICON));
        // put JSON main.temp
        data.put(TEMP, temp.getString(TEMP) + "°" + unit);
        // put JSON main.temp_max
        data.put(TEMP_MAX, temp.getString(TEMP_MAX)+ "°" + unit);
        // put JSON main.temp_min
        data.put(TEMP_MIN, temp.getString(TEMP_MIN)+ "°" + unit);
        // put JSON wind.speed
        data.put(WIND , wind.getString(SPEED));
        // put JSON visibility
        double vist = (Math.round(Double.parseDouble(visibility) / 10) / 100);
        data.put(VISIBILITY , ""+vist);
        Log.d(tag,data.toString());
    }
}
