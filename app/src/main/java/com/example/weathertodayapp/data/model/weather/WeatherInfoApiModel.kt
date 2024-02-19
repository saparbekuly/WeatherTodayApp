package com.example.weathertodayapp.data.model.weather

data class WeatherInfoApiModel(
    val base: String?,
    val clouds: CloudsApiModel?,
    val cod: Int?,
    val dt: Int?,
    val id: Int?,
    val main: MainApiModel?,
    val name: String?,
    val sys: SysApiModel?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<WeatherApiModel>?,
    val wind: WindApiModel?
)