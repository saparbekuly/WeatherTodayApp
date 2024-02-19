package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.domain.network.Response
import com.example.weathertodayapp.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double
    ): Response<WeatherInfo> =
        weatherRepository.getWeatherInfo(lat, lon)
}