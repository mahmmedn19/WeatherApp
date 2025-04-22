/**
 * @author Mohamed Naser.
 */
package com.project.data.local.database

import androidx.room.*

@Dao
interface WeatherDao {
    @Upsert
    suspend fun insertOrUpdate(weather: WeatherEntity)

    @Query("SELECT * FROM city_weather WHERE cityName = :city LIMIT 1")
    suspend fun getWeatherByCity(city: String): WeatherEntity?

    @Query("SELECT * FROM city_weather")
    suspend fun getAllSavedWeather(): List<WeatherEntity>
}