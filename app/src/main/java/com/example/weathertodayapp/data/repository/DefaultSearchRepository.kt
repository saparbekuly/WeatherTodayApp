package com.example.weathertodayapp.data.repository

import com.example.weathertodayapp.data.model.location.GeoLocationApiMapper
import com.example.weathertodayapp.data.service.SearchLocationService
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.network.Response
import com.example.weathertodayapp.domain.repository.SearchRepository
import com.example.weathertodayapp.utils.Constants.API_KEY
import com.example.weathertodayapp.utils.extension.catchError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultSearchRepository(
    private val service: SearchLocationService,
    private val geoLocationApiMapper: GeoLocationApiMapper
) : SearchRepository {
    override suspend fun getCityInfo(city: String): Response<List<CityItem>> = geoLocationApiMapper.map(
        try {
            withContext(Dispatchers.IO) {
                val data = service.getCityInfo(
                    city = city,
                    limit = LIMIT,
                    appId = API_KEY
                )
                Response.Success(data)
            }
        } catch (e: Exception) {
            e.catchError()
        })

    companion object {
        private const val LIMIT = 5
    }
}
