package com.example.weathertodayapp.data.repository

import com.example.weathertodayapp.data.local.LocationDao
import com.example.weathertodayapp.data.model.location.GeoLocationDBMapper
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.repository.LocationRepository

class DefaultLocationRepository(
    private val locationDao: LocationDao,
    private val geoLocationDBMapper: GeoLocationDBMapper
) : LocationRepository {

    override suspend fun addLocation(cityItem: CityItem) {
        locationDao.addLocation(geoLocationDBMapper.mapEntityToDBModel(cityItem))
    }

    override suspend fun deleteLocationById(locationId: Int) {
        locationDao.deleteLocationById(locationId)
    }

    override suspend fun getLocationList(): List<CityItem> {
        return geoLocationDBMapper.mapData(locationDao.getLocations())
    }
}