package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class OpenweatherAPI {

    public interface WeatherService1{
        @GET("data/2.5/weather?")
        Call<PostWeather>getCurrentWeatherData(@Query("lat") String lat,@Query("lon") String lon, @Query("APPID") String app_id);
    }
    public interface WeatherService2{
        @GET("data/2.5/weather?")
        Call<PostWeather>getCurrentWeatherData(@Query("city") String city,@Query("APPID") String app_id);
    }
}
