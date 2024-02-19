package com.example.weathertodayapp.domain.use_case

import com.example.weathertodayapp.R
import com.example.weathertodayapp.presentation.model.ValidationResult
import com.example.weathertodayapp.utils.UiText

class ValidateFieldUseCase {

    operator fun invoke(text: String): ValidationResult {
        if (text.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.field_is_required)
            )
        }

        return ValidationResult(successful = true)
    }
}