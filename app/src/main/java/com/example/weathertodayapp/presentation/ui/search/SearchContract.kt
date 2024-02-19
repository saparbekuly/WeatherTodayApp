package com.example.weathertodayapp.presentation.ui.search

import androidx.compose.runtime.MutableState
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.model.TextFieldUiState
import com.example.weathertodayapp.presentation.mvi.UiEffect
import com.example.weathertodayapp.presentation.mvi.UiEvent
import com.example.weathertodayapp.presentation.mvi.UiState

interface SearchContract {
    sealed interface Event : UiEvent {
        data object OnSearchClicked : Event
        data object OnCityFieldChanged : Event
        data class OnAddLocation(val cityItem: CityItem) : Event
    }

    data class State(
        val geoLocationState: ResourceUiState<List<CityItem>>,
        val locationFieldState: TextFieldUiState,
        val isButtonEnabled: MutableState<Boolean>,
    ) : UiState

    sealed interface Effect : UiEffect {
        data object OnAddedSuccessfully : Effect
    }
}


