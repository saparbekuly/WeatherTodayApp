package com.example.weathertodayapp.domain.repository

import com.example.weathertodayapp.domain.model.location.CityItem

interface LocationRepository {
    suspend fun addLocation(cityItem: CityItem)
    suspend fun deleteLocationById(locationId: Int)
    suspend fun getLocationList(): List<CityItem>
}