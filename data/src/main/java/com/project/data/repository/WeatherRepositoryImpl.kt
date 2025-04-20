package com.project.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.project.data.local.data_store.SearchHistoryManager
import com.project.data.local.database.WeatherDao
import com.project.data.local.database.WeatherEntity
import com.project.data.mapper.*
import com.project.domain.model.CurrentWeather
import com.project.domain.model.DailyForecast
import com.project.domain.repository.WeatherRepository
import com.project.data.remote.network.WeatherApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService,
    private val weatherDao: WeatherDao,
    private val historyManager: SearchHistoryManager
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override suspend fun getCurrentWeather(city: String): Result<CurrentWeather> {
        return try {
            val response = api.getCurrentWeather(city)
            if (response.isSuccessful) {
                response.body()?.let { dto ->
                    val domain = dto.toDomain()

                    // Save to Room
                    weatherDao.insertOrUpdate(domain.toEntity())

                    // Save city name to DataStore
                    historyManager.saveCity(city)

                    Result.success(domain)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getForecast(city: String): Result<List<DailyForecast>> {
        return try {
            val response = api.getFiveDayThreeHourForecast(city)
            if (response.isSuccessful) {
                response.body()?.let { dto ->
                    Result.success(dto.toDailyForecast())
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getLastSearchedCities(): Flow<List<String>> {
        return historyManager.lastFiveCities
    }

    override suspend fun getWeatherForCity(city: String): Result<CurrentWeather> {
        return try {
            val entity = weatherDao.getWeatherByCity(city)
            if (entity != null) {
                Result.success(entity.toDomain())
            } else {
                Result.failure(Exception("No data found for city: $city"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}