package com.example.weathertodayapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weathertodayapp.domain.model.location.CityItem
import com.example.weathertodayapp.presentation.ui.locations.LocationScreen
import com.example.weathertodayapp.presentation.ui.search.SearchScreen
import com.example.weathertodayapp.presentation.ui.weather.WeatherScreen
import com.example.weathertodayapp.utils.Screen.Companion.CITY_ITEM
import com.example.weathertodayapp.utils.extension.ParcelableNavType
import com.example.weathertodayapp.utils.extension.parcelable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Location.route) {
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.Location.route) {
            LocationScreen(navController = navController)
        }
        composable("${Screen.WeatherDetail.route}/{$CITY_ITEM}",
            arguments = listOf(
                navArgument(CITY_ITEM) {
                    type = ParcelableNavType(CityItem::class.java)
                }
            )) {
            val cityItem = it.arguments?.parcelable<CityItem>(CITY_ITEM)
            WeatherScreen(navController = navController, cityItem = cityItem)
        }
    }
}