package com.example.weathertodayapp.utils

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class UiText : Parcelable {
    @Parcelize
    data class DynamicString(val value: String) : UiText(), Parcelable

    @Parcelize
    class StringResource(
        val stringResource: Int
    ) : UiText(), Parcelable

    @Composable
    fun getText(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(stringResource)
        }
    }
}