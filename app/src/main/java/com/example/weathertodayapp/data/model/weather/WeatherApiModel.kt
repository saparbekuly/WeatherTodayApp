package com.example.weathertodayapp.data.model.weather

data class WeatherApiModel(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)