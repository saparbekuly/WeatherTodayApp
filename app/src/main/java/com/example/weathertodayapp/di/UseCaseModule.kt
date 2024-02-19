package com.example.weathertodayapp.di

import com.example.weathertodayapp.domain.use_case.AddLocationUseCase
import com.example.weathertodayapp.domain.use_case.DeleteLocationUseCase
import com.example.weathertodayapp.domain.use_case.GetLocationListUseCase
import com.example.weathertodayapp.domain.use_case.GetWeatherUseCase
import com.example.weathertodayapp.domain.use_case.SearchCityInfoUseCase
import com.example.weathertodayapp.domain.use_case.ValidateFieldUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val useCasesModule: Module = module {

    //use cases
    factoryOf(::SearchCityInfoUseCase)
    factoryOf(::GetLocationListUseCase)
    factoryOf(::AddLocationUseCase)
    factoryOf(::DeleteLocationUseCase)
    factoryOf(::GetWeatherUseCase)
    factoryOf(::ValidateFieldUseCase)
}


