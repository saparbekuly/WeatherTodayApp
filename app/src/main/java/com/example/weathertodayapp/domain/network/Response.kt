package com.example.weathertodayapp.domain.network

import com.example.weathertodayapp.R
import com.example.weathertodayapp.presentation.model.ResourceUiState
import com.example.weathertodayapp.utils.UiText
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


sealed class Response<out T> {

    data class Success<out T>(val data: T) : Response<T>()

    data class Error(val error: UiText) : Response<Nothing>()

    data object NetworkError : Response<Nothing>()

    @OptIn(ExperimentalContracts::class)
    companion object {

        inline fun <T> Response<T>.onSuccess(action: (value: T) -> Unit): Response<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            if (this is Success) action(data)
            return this
        }

        inline fun <T> Response<T>.onError(action: (error: UiText) -> Unit): Response<T> {
            contract {
                callsInPlace(action, InvocationKind.AT_MOST_ONCE)
            }
            when (this) {
                is Error -> {
                    action(error)
                }

                is NetworkError -> {
                    action(UiText.StringResource(R.string.no_internet_connection))
                }

                else -> {}
            }
            return this
        }
    }
}

fun <T> Response<T>.toResourceUiState(): ResourceUiState<T> {
    return when (this) {
        is Response.Success -> {
            ResourceUiState.Success(this.data)
        }

        is Response.Error -> {
            ResourceUiState.Error(this.error)
        }

        is Response.NetworkError -> {
            ResourceUiState.NetworkError
        }
    }
}

fun <T> List<T>.toResourceUiState(): ResourceUiState<List<T>> {
    return if (this.isNotEmpty()) {
        ResourceUiState.Success(this)
    } else {
        ResourceUiState.Empty
    }
}

fun <T> Response<T>.toResourceUiStateUnit(): ResourceUiState<Unit> {
    return when (this) {
        is Response.Success -> {
            ResourceUiState.Success(Unit)
        }

        is Response.Error -> {
            ResourceUiState.Error(this.error)
        }

        is Response.NetworkError -> {
            ResourceUiState.NetworkError
        }
    }
}

fun <T> Response<T>.toResourceUiState(message: UiText): ResourceUiState<Unit> {
    return when (this) {
        is Response.Success -> {
            ResourceUiState.Success(Unit, message)
        }

        is Response.Error -> {
            ResourceUiState.Error(this.error)
        }

        is Response.NetworkError -> {
            ResourceUiState.NetworkError
        }
    }
}