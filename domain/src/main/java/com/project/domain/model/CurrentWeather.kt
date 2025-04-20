package com.project.domain.model

data class CurrentWeather(
    val city: String,
    val country: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val humidity: Int,
    val feelsLike: Double,
    val sunrise: Long,
    val sunset: Long,
    val timestamp: Long
)