package com.example.weathertodayapp.data.model.location

import com.example.weathertodayapp.domain.map.Mapper
import com.example.weathertodayapp.domain.model.location.CityItem

class GeoLocationApiMapper : Mapper<List<CityItemApiModel>, List<CityItem>>() {

    override fun mapData(from: List<CityItemApiModel>): List<CityItem> =
        from.map {
            CityItem(
                country = it.country ?: "",
                lat = it.lat ?: 0.0,
                lon = it.lon ?: 0.0,
                name = it.name ?: "",
                state = it.state ?: ""
            )
        }
}
