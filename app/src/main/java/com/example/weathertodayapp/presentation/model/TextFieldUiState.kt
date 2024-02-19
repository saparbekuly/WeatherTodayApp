package com.example.weathertodayapp.presentation.model

import androidx.compose.runtime.MutableState

data class TextFieldUiState(
    var input: MutableState<String>,
    var validationResult: MutableState<ValidationResult>,
    val onFieldChanged: () -> Unit? = {}
)
