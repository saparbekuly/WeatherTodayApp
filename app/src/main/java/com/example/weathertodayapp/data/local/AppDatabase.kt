package com.example.weathertodayapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weathertodayapp.data.model.location.CityItemDBModel

@Database(entities = [CityItemDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao
}