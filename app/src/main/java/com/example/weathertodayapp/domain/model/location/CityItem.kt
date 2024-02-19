package com.example.weathertodayapp.domain.model.location

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityItem(
    var id: Int = UNDEFINED_ID,
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
