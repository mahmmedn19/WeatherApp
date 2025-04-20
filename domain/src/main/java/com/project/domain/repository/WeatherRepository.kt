/**
 * @author Mohamed Naser.
 */

package com.project.domain.repository

import com.project.domain.model.CurrentWeather
import com.project.domain.model.DailyForecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Result<CurrentWeather>
    suspend fun getForecast(city: String): Result<List<DailyForecast>>
    fun getLastSearchedCities(): Flow<List<String>>
    suspend fun getWeatherForCity(city: String): Result<CurrentWeather>
}