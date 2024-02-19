package com.example.weathertodayapp.presentation.ui.locations

import androidx.lifecycle.viewModelScope
import com.example.weathertodayapp.domain.interactors.use_case.DeleteLocationUseCase
import com.example.weathertodayapp.domain.interactors.use_case.GetLocationListUseCase
import com.example.weathertodayapp.domain.network.toResourceUiState
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getLocationListUseCase: GetLocationListUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase
) :
    BaseViewModel<LocationContract.State, LocationContract.Event, LocationContract.Effect>() {
    override fun createInitialState(): LocationContract.State {
        return LocationContract.State(
            locationListState = ResourceUiState.Loading
        )
    }

    init {
        fetchData()
    }

    override fun handleEvent(event: LocationContract.Event) {
        when (event) {
            is LocationContract.Event.OnLocationDeleted -> {
                deleteLocation(event.id)
            }

            LocationContract.Event.OnInit -> {
                fetchData()
            }
        }
    }

    private fun deleteLocation(cityId: Int) {
        viewModelScope.launch {
            deleteLocationUseCase(cityId)
        }
        fetchData()
    }

    private fun fetchData() {
        setState { copy(locationListState = ResourceUiState.Loading) }
        viewModelScope.launch {
            val result = getLocationListUseCase()
            setState {
                copy(
                    locationListState = result.toResourceUiState()
                )
            }
        }
    }
}