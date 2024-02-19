package com.example.weathertodayapp.di

import com.example.weathertodayapp.data.model.location.GeoLocationApiMapper
import com.example.weathertodayapp.data.model.location.GeoLocationDBMapper
import com.example.weathertodayapp.data.model.weather.WeatherApiMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val mapperModule = module {
    singleOf(::GeoLocationApiMapper)
    singleOf(::GeoLocationDBMapper)
    singleOf(::WeatherApiMapper)
}