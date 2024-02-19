package com.example.weathertodayapp.presentation.ui.locations

import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.mvi.UiEffect
import com.example.weathertodayapp.presentation.mvi.UiEvent
import com.example.weathertodayapp.presentation.mvi.UiState

interface LocationContract {
    sealed interface Event : UiEvent {

        data class OnLocationDeleted(val id: Int) : Event

        data object OnInit : Event
    }

    data class State(
        val locationListState: ResourceUiState<List<CityItem>>,
    ) : UiState

    sealed interface Effect : UiEffect
}


