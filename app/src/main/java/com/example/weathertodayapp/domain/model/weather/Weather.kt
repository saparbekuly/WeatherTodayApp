package com.example.weathertodayapp.domain.model.weather

data class Weather(
    val description: String,
    val icon: Int,
    val id: Int,
    val main: String
)