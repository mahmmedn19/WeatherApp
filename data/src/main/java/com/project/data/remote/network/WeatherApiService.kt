/**
 * @author Mohamed Naser.
 */
package com.project.data.remote.network

import com.project.core.BuildConfig
import com.project.data.remote.dto.CurrentWeatherDto
import com.project.data.remote.dto.ForecastResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): Response<CurrentWeatherDto>

    @GET("data/2.5/forecast")
    suspend fun getFiveDayThreeHourForecast(
        @Query("q") city: String,
        @Query("cnt") days: Int = 7,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): Response<ForecastResponseDto>
}