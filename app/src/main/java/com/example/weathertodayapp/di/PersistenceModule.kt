package com.example.weathertodayapp.di

import android.content.Context
import androidx.room.Room
import com.example.weathertodayapp.data.local.AppDatabase
import com.example.weathertodayapp.data.local.LocationDao
import com.example.weathertodayapp.utils.Constants.LOCATION_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


fun provideAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        LOCATION_DB
    ).build()
}

fun provideLocationDao(database: AppDatabase): LocationDao {
    return database.locationDao()
}

val persistenceModule = module {
    single { provideAppDatabase(androidContext()) }
    single { provideLocationDao(get()) }
}


