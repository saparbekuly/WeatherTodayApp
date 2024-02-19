package com.example.weathertodayapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weathertodayapp.data.model.location.CityItemDBModel

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocation(cityItemDBModel: CityItemDBModel)

    @Query("DELETE FROM locations WHERE id = :locationId")
    suspend fun deleteLocationById(locationId: Int)

    @Query("SELECT * FROM locations")
    suspend fun getLocations(): List<CityItemDBModel>
}