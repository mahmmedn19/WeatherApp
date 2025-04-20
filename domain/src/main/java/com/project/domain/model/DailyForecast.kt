package com.project.domain.model

data class DailyForecast(
    val date: String,
    val avgTemp: Double,
    val icon: String,
    val condition: String
)