package com.example.weathertodayapp.data.model.weather

import com.example.weathertodayapp.domain.map.Mapper
import com.example.weathertodayapp.domain.model.weather.Main
import com.example.weathertodayapp.domain.model.weather.Sys
import com.example.weathertodayapp.domain.model.weather.Weather
import com.example.weathertodayapp.domain.model.weather.WeatherInfo
import com.example.weathertodayapp.domain.model.weather.WeatherType
import com.example.weathertodayapp.domain.model.weather.Wind

class WeatherApiMapper : Mapper<WeatherInfoApiModel, WeatherInfo>() {

    override fun mapData(from: WeatherInfoApiModel): WeatherInfo =
        WeatherInfo(
            base = from.base ?: "",
            clouds = from.clouds?.all ?: 0,
            cod = from.cod ?: 0,
            dt = from.dt ?: 0,
            id = from.id ?: 0,
            main = from.main?.let {
                Main(
                    feelsLike = it.feelsLike ?: 0.0,
                    humidity = it.humidity ?: 0,
                    pressure = it.pressure ?: 0,
                    temp = it.temp ?: 0.0,
                    tempMax = it.tempMax ?: 0.0,
                    tempMin = it.tempMin ?: 0.0,
                    grndLevel = it.grndLevel ?: 0,
                    seaLevel = it.seaLevel ?: 0
                )
            },
            name = from.name ?: "",
            sys = from.sys?.let {
                Sys(
                    country = it.country,
                    sunrise = it.sunrise,
                    sunset = it.sunset,
                )
            },
            timezone = from.timezone ?: 0,
            visibility = from.visibility ?: 0,
            weather = from.weather?.map {
                Weather(
                    description = it.description ?: "",
                    icon = WeatherType.fromCode(it.id).iconRes,
                    id = it.id ?: 0,
                    main = it.main ?: ""
                )
            } ?: emptyList(),
            wind = from.wind?.let {
                Wind(
                    deg = it.deg,
                    speed = it.speed,
                    gust = it.gust
                )
            }
        )

}
