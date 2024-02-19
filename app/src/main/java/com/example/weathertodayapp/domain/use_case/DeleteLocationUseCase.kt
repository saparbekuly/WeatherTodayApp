package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.domain.repository.LocationRepository

class DeleteLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(id: Int) =
        locationRepository.deleteLocationById(id)
}