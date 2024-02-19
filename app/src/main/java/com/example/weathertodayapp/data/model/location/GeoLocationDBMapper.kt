package com.example.weathertodayapp.data.model.location

import com.example.weathertodayapp.domain.map.Mapper
import com.example.weathertodayapp.domain.model.location.CityItem

class GeoLocationDBMapper : Mapper<List<CityItemDBModel>, List<CityItem>>() {

    override fun mapData(from: List<CityItemDBModel>): List<CityItem> =
        from.map {
            CityItem(
                id = it.id,
                country = it.country ?: "",
                lat = it.lat ?: 0.0,
                lon = it.lon ?: 0.0,
                name = it.name ?: "",
                state = it.state ?: ""
            )
        }

    fun mapEntityToDBModel(cityItem: CityItem): CityItemDBModel = CityItemDBModel(
        id = cityItem.id, // Assuming autoGenerate is true, you can set this to 0 for new entries
        name = cityItem.name,
        lat = cityItem.lat,
        lon = cityItem.lon,
        state = cityItem.state,
        country = cityItem.country
    )

    fun mapDBModelToEntity(cityItemDBModel: CityItemDBModel?): CityItem? {
        if (cityItemDBModel == null)
            return null
        return CityItem(
            id = cityItemDBModel.id,
            name = cityItemDBModel.name ?: "",
            lat = cityItemDBModel.lat ?: 0.0,
            lon = cityItemDBModel.lon ?: 0.0,
            state = cityItemDBModel.state ?: "",
            country = cityItemDBModel.country ?: ""
        )
    }
}
