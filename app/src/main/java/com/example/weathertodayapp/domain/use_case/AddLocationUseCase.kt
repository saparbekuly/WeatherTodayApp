package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.repository.LocationRepository

class AddLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(cityItem: CityItem) =
        locationRepository.addLocation(cityItem)
}