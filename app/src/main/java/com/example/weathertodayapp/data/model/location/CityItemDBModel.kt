package com.example.weathertodayapp.data.model.location

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.weathertodayapp.utils.Constants.LOCATIONS

@Entity(
    tableName = LOCATIONS,
    indices = [Index(value = ["name", "lat", "lon", "state", "country"], unique = true)]
)
data class CityItemDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String?,
    val lat: Double?,
    val lon: Double?,
    val state: String?,
    val country: String?
)