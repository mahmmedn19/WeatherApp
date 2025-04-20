package com.project.currentweather

import com.project.domain.model.CurrentWeather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val weather: CurrentWeather? = null,
    val error: String? = null
)