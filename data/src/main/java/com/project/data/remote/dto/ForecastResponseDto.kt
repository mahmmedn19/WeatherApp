package com.project.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponseDto(
    @SerializedName("list") val list: List<ForecastItemDto>,
    @SerializedName("city") val city: CityDto
)

@Serializable
data class ForecastItemDto(
    @SerializedName("dt") val timestamp: Long,
    @SerializedName("main") val main: MainDto,
    @SerializedName("weather") val weather: List<WeatherDto>,
    @SerializedName("dt_txt") val dateText: String
)

@Serializable
data class CityDto(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)
