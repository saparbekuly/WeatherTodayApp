package com.example.weathertodayapp.data.repository

import com.example.weathertodayapp.data.model.weather.WeatherApiMapper
import com.example.weathertodayapp.data.service.WeatherService
import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.domain.network.Response
import com.example.weathertodayapp.domain.repository.WeatherRepository
import com.example.weathertodayapp.utils.Constants.API_KEY
import com.example.weathertodayapp.utils.extension.catchError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultWeatherRepository(
    private val service: WeatherService,
    private val weatherApiMapper: WeatherApiMapper
) : WeatherRepository {
    override suspend fun getWeatherInfo(
        lat: Double,
        lon: Double
    ): Response<WeatherInfo> = weatherApiMapper.map(
        try {
            withContext(Dispatchers.IO) {
                val data = service.getWeatherInfo(
                    lat = lat,
                    lon = lon,
                    appId = API_KEY,
                    units = METRIC
                )
                Response.Success(data)
            }
        } catch (e: Exception) {
            e.catchError()
        })

    companion object {
        private const val METRIC = "metric"
    }
}
