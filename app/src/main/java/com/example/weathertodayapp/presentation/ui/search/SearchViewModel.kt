package com.example.weathertodayapp.presentation.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.weathertodayapp.domain.interactors.use_case.AddLocationUseCase
import com.example.weathertodayapp.domain.interactors.use_case.SearchCityInfoUseCase
import com.example.weathertodayapp.domain.interactors.validation.ValidateField
import com.example.weathertodayapp.domain.network.toResourceUiState
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.presentation.model.TextFieldUiState
import com.example.weathertodayapp.presentation.model.ValidationResult
import com.example.weathertodayapp.presentation.mvi.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(
    private val validateField: ValidateField,
    private val searchCityInfoUseCase: SearchCityInfoUseCase,
    private val addLocationUseCase: AddLocationUseCase
) :
    BaseViewModel<SearchContract.State, SearchContract.Event, SearchContract.Effect>() {
    override fun createInitialState(): SearchContract.State {
        return SearchContract.State(
            locationFieldState = TextFieldUiState(
                input = mutableStateOf(""),
                validationResult = mutableStateOf(ValidationResult(successful = true)),
                onFieldChanged = { this.setEvent(SearchContract.Event.OnCityFieldChanged) }
            ),
            isButtonEnabled = mutableStateOf(false),
            geoLocationState = ResourceUiState.Idle
        )
    }


    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            SearchContract.Event.OnSearchClicked -> {
                fetchData(state.locationFieldState.input.value)
            }

            SearchContract.Event.OnCityFieldChanged -> {
                with(currentState) {
                    val validateLocationResult = validateField(locationFieldState.input.value)
                    locationFieldState.validationResult.value = validateLocationResult
                    isButtonEnabled.value =
                        validateLocationResult.successful
                }
            }

            is SearchContract.Event.OnAddLocation -> {
                viewModelScope.launch {
                    addLocationUseCase(event.cityItem)
                    setEffect {
                        SearchContract.Effect.OnAddedSuccessfully
                    }
                }
            }
        }
    }

    private fun fetchData(city: String) {
        setState { copy(geoLocationState = ResourceUiState.Loading) }
        viewModelScope.launch {
            val result = searchCityInfoUseCase(city)
            setState {
                copy(
                    geoLocationState = result.toResourceUiState()
                )
            }
        }
    }
}