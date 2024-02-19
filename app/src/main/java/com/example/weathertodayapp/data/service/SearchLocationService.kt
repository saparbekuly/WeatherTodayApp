package com.example.weathertodayapp.data.service

import com.example.weathertodayapp.data.model.location.CityItemApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchLocationService {

    @GET("geo/1.0/direct")
    suspend fun getCityInfo(
        @Query("q") city: String,
        @Query("limit") limit: Int,
        @Query("appid") appId: String,
    ): List<CityItemApiModel>
}