package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.network.Response
import com.example.weathertodayapp.domain.repository.SearchRepository

class SearchCityInfoUseCase(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(
        city: String
    ): Response<List<CityItem>> =
        searchRepository.getCityInfo(city)
}