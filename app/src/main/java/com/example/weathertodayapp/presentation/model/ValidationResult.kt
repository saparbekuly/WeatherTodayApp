package com.example.weathertodayapp.presentation.model

import com.example.weathertodayapp.utils.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null,
)