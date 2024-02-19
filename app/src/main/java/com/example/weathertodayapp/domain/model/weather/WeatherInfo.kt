package com.example.weathertodayapp.domain.model.weather

data class WeatherInfo(
    val base: String,
    val clouds: Int,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: Main?,
    val name: String,
    val sys: Sys?,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>? = emptyList(),
    val wind: Wind?
)