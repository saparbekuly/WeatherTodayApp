package com.example.weathertodayapp.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.example.weathertodayapp.domain.use_case.DeleteLocationUseCase
import com.example.weathertodayapp.domain.use_case.GetLocationListUseCase
import com.example.weathertodayapp.domain.use_case.GetWeatherUseCase
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.network.toResourceUiState
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase,
    val cityItem : CityItem
) :
    BaseViewModel<WeatherContract.State, WeatherContract.Event, WeatherContract.Effect>() {
    override fun createInitialState(): WeatherContract.State {
        return WeatherContract.State(
            weatherState = ResourceUiState.Loading
        )
    }

    init {
        fetchData()
    }

    override fun handleEvent(event: WeatherContract.Event) {}


    private fun fetchData() {
        setState { copy(weatherState = ResourceUiState.Loading) }
        viewModelScope.launch {
            val result = getWeatherUseCase(cityItem.lat, cityItem.lon)
            setState {
                copy(
                    weatherState = result.toResourceUiState()
                )
            }
        }
    }
}