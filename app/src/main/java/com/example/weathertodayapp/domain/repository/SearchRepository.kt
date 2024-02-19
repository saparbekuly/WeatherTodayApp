package com.example.weathertodayapp.domain.repository

import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.network.Response


interface SearchRepository {
    suspend fun getCityInfo(city: String): Response<List<CityItem>>
}