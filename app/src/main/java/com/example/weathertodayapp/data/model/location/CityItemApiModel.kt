package com.example.weathertodayapp.data.model.location

import com.google.gson.annotations.SerializedName

data class CityItemApiModel(
    val country: String?,
    val lat: Double?,
    @SerializedName("local_names")
    val localNames: LocalNamesApiModel?,
    val lon: Double?,
    val name: String?,
    val state: String?
)