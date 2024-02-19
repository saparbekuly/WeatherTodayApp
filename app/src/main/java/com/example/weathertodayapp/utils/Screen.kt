package com.example.weathertodayapp.utils

sealed class Screen(val route: String) {
    data object Search : Screen("nav_search")
    data object Location : Screen("nav_location")
    data object WeatherDetail : Screen("nav_weather_detail")

    companion object{
        const val CITY_ITEM = "cityItem"
    }
}