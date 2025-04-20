package com.project.forecast

data class ForecastState(
    val isLoading: Boolean = false,
    val forecasts: List<ForecastUiModel> = emptyList(),
    val errorMessage: String? = null
)

data class ForecastUiModel(
    val date: String,
    val temperature: String,
    val condition: String,
    val iconUrl: String
)