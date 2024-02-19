package com.example.weathertodayapp.di

import com.example.weathertodayapp.presentation.ui.locations.LocationViewModel
import com.example.weathertodayapp.presentation.ui.search.SearchViewModel
import com.example.weathertodayapp.presentation.ui.weather.WeatherViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::SearchViewModel)
    factoryOf(::LocationViewModel)
    factory { params ->
        WeatherViewModel(
            get(),
            params.get()
        )
    }

}

