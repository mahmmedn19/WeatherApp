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

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "99f6b894bf488e9df51eb930eb95ec96"
    ): Response<CurrentWeatherDto>

    @GET("forecast")
    suspend fun getFiveDayThreeHourForecast(
        @Query("q") city: String,
        @Query("cnt") days: Int = 5,
        @Query("appid") apiKey: String = "99f6b894bf488e9df51eb930eb95ec96",
        @Query("units") units: String = "metric"
    ): Response<ForecastResponseDto>
}