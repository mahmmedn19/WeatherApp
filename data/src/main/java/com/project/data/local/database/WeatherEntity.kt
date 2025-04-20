package com.project.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class WeatherEntity(
    @PrimaryKey val cityName: String,
    val country: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val windSpeed: Double = 0.0,
    val humidity: Int = 0,
    val feelsLike: Double = 0.0,
    val sunrise: Long = 0L,
    val sunset: Long = 0L,
    val updatedAt: Long = System.currentTimeMillis()
)
