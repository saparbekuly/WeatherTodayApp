package com.example.weathertodayapp.presentation.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.weathertodayapp.R
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.presentation.state.ManagementResourceUiState
import com.example.weathertodayapp.presentation.ui.common.CustomTopAppBar
import com.example.weathertodayapp.ui.theme.Paddings
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WeatherScreen(
    cityItem: CityItem?,
    weatherViewModel: WeatherViewModel = koinViewModel {
        parametersOf(cityItem)
    }
) {
    val weatherUiState by weatherViewModel.uiState.collectAsState()

    WeatherContent(
        state = weatherUiState,
        cityItem
    )
}

@Composable
private fun WeatherContent(
    state: WeatherContract.State,
    cityItem: CityItem?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.run {
            fillMaxSize().padding(
                start = Paddings.medium,
                end = Paddings.medium
            )
        }
    ) {
        CustomTopAppBar(
            title = stringResource(R.string.weather_today)
        )
        ManagementResourceUiState(
            resourceUiState = state.weatherState,
            successView = {
                WeatherView(weatherInfo = it, cityItem = cityItem)
            },
            loadingView = {
            },
            onCheckAgain = {},
            onTryAgain = {},
        )
    }
}

@Composable
fun WeatherView(weatherInfo: WeatherInfo, cityItem: CityItem?) {
    Column {
        Text(text = stringResource(R.string.city_name, cityItem?.name ?: ""))
        Text(text = stringResource(R.string.weather, weatherInfo.weather?.first()?.main ?: ""))
        Text(
            text = stringResource(
                R.string.weather_description,
                weatherInfo.weather?.first()?.description ?: ""
            )
        )
        Text(text = stringResource(R.string.temp, weatherInfo.main?.temp?.toFloat() ?: 0.0f))
        Text(
            text = stringResource(
                R.string.temp_feels,
                weatherInfo.main?.feelsLike?.toFloat() ?: 0.0f
            )
        )
        Text(text = stringResource(R.string.temp_max, weatherInfo.main?.tempMax?.toFloat() ?: 0.0f))
        Text(text = stringResource(R.string.temp_min, weatherInfo.main?.tempMin?.toFloat() ?: 0.0f))
        Text(
            text = stringResource(
                R.string.sea_level,
                weatherInfo.main?.seaLevel.toString() ?: ""
            )
        )
    }
}