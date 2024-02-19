package com.example.weathertodayapp.data.service

import com.example.weathertodayapp.data.model.weather.WeatherInfoApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getWeatherInfo(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") appId: String,
    ): WeatherInfoApiModel
}