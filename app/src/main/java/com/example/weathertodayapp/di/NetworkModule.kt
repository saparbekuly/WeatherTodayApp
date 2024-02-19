package com.example.weathertodayapp.di

import com.example.weathertodayapp.data.service.SearchLocationService
import com.example.weathertodayapp.data.service.WeatherService
import com.example.weathertodayapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    singleOf(::provideHttpClient)
    singleOf(::provideSearchLocationService)
    singleOf(::provideWeatherService)
}

fun provideHttpClient(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Retrofit.Builder()
        .client(
            OkHttpClient().newBuilder().addInterceptor(
                interceptor
            ).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideSearchLocationService(retrofit: Retrofit): SearchLocationService {
    return retrofit.create(SearchLocationService::class.java)
}

fun provideWeatherService(retrofit: Retrofit): WeatherService {
    return retrofit.create(WeatherService::class.java)
}