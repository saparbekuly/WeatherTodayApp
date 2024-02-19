package com.example.weathertodayapp.domain.model.weather

import com.example.weathertodayapp.R

enum class WeatherType(val code: Int, val iconRes: Int) {

    // Thunderstorm
    ThunderstormWithLightRain(200, R.drawable.ic_11d),
    ThunderstormWithRain(201, R.drawable.ic_11d),
    ThunderstormWithHeavyRain(202, R.drawable.ic_11d),
    LightThunderstorm(210, R.drawable.ic_11d),
    Thunderstorm(211, R.drawable.ic_11d),
    HeavyThunderstorm(212, R.drawable.ic_11d),
    RaggedThunderstorm(221, R.drawable.ic_11d),
    ThunderstormWithLightDrizzle(230, R.drawable.ic_11d),
    ThunderstormWithDrizzle(231, R.drawable.ic_11d),
    ThunderstormWithHeavyDrizzle(232, R.drawable.ic_11d),

    // Drizzle
    LightIntensityDrizzle(300, R.drawable.ic_09d),
    Drizzle(301, R.drawable.ic_09d),
    HeavyIntensityDrizzle(302, R.drawable.ic_09d),
    LightIntensityDrizzleRain(310, R.drawable.ic_09d),
    DrizzleRain(311, R.drawable.ic_09d),
    HeavyIntensityDrizzleRain(312, R.drawable.ic_09d),
    ShowerRainAndDrizzle(313, R.drawable.ic_09d),
    HeavyShowerRainAndDrizzle(314, R.drawable.ic_09d),
    ShowerDrizzle(321, R.drawable.ic_09d),

    // Rain
    LightRain(500, R.drawable.ic_10d),
    ModerateRain(501, R.drawable.ic_10d),
    HeavyIntensityRain(502, R.drawable.ic_10d),
    VeryHeavyRain(503, R.drawable.ic_10d),
    ExtremeRain(504, R.drawable.ic_10d),
    FreezingRain(511, R.drawable.ic_13d),
    LightIntensityShowerRain(520, R.drawable.ic_09d),
    ShowerRain(521, R.drawable.ic_09d),
    HeavyIntensityShowerRain(522, R.drawable.ic_09d),
    RaggedShowerRain(531, R.drawable.ic_09d),

    // Snow
    LightSnow(600, R.drawable.ic_13d),
    Snow(601, R.drawable.ic_13d),
    HeavySnow(602, R.drawable.ic_13d),
    Sleet(611, R.drawable.ic_13d),
    LightShowerSleet(612, R.drawable.ic_13d),
    ShowerSleet(613, R.drawable.ic_13d),
    LightRainAndSnow(615, R.drawable.ic_13d),
    RainAndSnow(616, R.drawable.ic_13d),
    LightShowerSnow(620, R.drawable.ic_13d),
    ShowerSnow(621, R.drawable.ic_13d),
    HeavyShowerSnow(622, R.drawable.ic_13d),

    // Mist, Smoke, Haze, Dust, Fog, Sand, Dust Whirls, Volcanic Ash, Squalls, Tornado
    Mist(701, R.drawable.ic_50d),
    Smoke(711, R.drawable.ic_50d),
    Haze(721, R.drawable.ic_50d),
    Dust(731, R.drawable.ic_50d),
    Fog(741, R.drawable.ic_50d),
    Sand(751, R.drawable.ic_50d),
    DustWhirls(761, R.drawable.ic_50d),
    VolcanicAsh(762, R.drawable.ic_50d),
    Squalls(771, R.drawable.ic_50d),
    Tornado(781, R.drawable.ic_50d),

    // Clear
    ClearSky(800, R.drawable.ic_01d), // Assuming "00" was meant to be "800" for clear skies

    // CloudsApiModel
    FewClouds(801, R.drawable.ic_02d),
    ScatteredClouds(802, R.drawable.ic_03d),
    BrokenClouds(803, R.drawable.ic_04d),
    OvercastClouds(804, R.drawable.ic_04d);

    companion object {
        fun fromCode(code: Int): WeatherType = entries.find { it.code == code } ?: ClearSky
    }
}