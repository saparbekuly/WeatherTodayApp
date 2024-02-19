package com.example.weathertodayapp.utils

import android.app.Application
import com.example.weathertodayapp.di.mapperModule
import com.example.weathertodayapp.di.networkModule
import com.example.weathertodayapp.di.persistenceModule
import com.example.weathertodayapp.di.repositoryModule
import com.example.weathertodayapp.di.useCasesModule
import com.example.weathertodayapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                repositoryModule,
                useCasesModule,
                viewModelModule,
                networkModule,
                mapperModule,
                persistenceModule
            )
        }
    }
}
