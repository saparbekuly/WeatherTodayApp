package com.example.weathertodayapp.di

import com.example.weathertodayapp.data.repository.DefaultLocationRepository
import com.example.weathertodayapp.data.repository.DefaultSearchRepository
import com.example.weathertodayapp.data.repository.DefaultWeatherRepository
import com.example.weathertodayapp.domain.repository.LocationRepository
import com.example.weathertodayapp.domain.repository.SearchRepository
import com.example.weathertodayapp.domain.repository.WeatherRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::DefaultSearchRepository).bind(SearchRepository::class)
    factoryOf(::DefaultLocationRepository).bind(LocationRepository::class)
    factoryOf(::DefaultWeatherRepository).bind(WeatherRepository::class)
}