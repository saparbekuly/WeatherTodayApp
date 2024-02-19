package com.example.weathertodayapp.domain.repository

import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.domain.network.Response

interface WeatherRepository {
    suspend fun getWeatherInfo(
        lat: Double,
        lon: Double
    ): Response<WeatherInfo>
}