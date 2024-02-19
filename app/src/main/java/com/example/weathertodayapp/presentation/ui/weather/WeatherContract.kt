package com.example.weathertodayapp.presentation.ui.weather

import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.mvi.UiEffect
import com.example.weathertodayapp.presentation.mvi.UiEvent
import com.example.weathertodayapp.presentation.mvi.UiState

interface WeatherContract {
    sealed interface Event : UiEvent

    data class State(
        val weatherState: ResourceUiState<WeatherInfo>,
    ) : UiState

    sealed interface Effect : UiEffect
}


