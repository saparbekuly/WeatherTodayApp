package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.repository.LocationRepository

class GetLocationListUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): List<CityItem> =
        locationRepository.getLocationList()
}