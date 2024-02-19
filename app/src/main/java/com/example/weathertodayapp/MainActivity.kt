package com.example.weathertodayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.weathertodayapp.ui.theme.WeatherTodayAppTheme
import com.example.weathertodayapp.ui.theme.white
import com.example.weathertodayapp.ui.theme.yellow
import com.example.weathertodayapp.utils.Navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            systemUiController.setStatusBarColor(
                color = white
            )
            systemUiController.setNavigationBarColor(
                color = white
            )
            WeatherTodayAppTheme {
                // A surface container using the 'background' color from the theme
                Navigation()
            }
        }
    }
}

