/**
 * @author Mohamed Naser.
 */

package com.project.domain.usecase

import com.project.domain.model.CurrentWeather
import com.project.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<CurrentWeather> {
        val remoteResult = repository.getCurrentWeather(city)

        return if (remoteResult.isSuccess) {
            remoteResult
        } else {
            // Fallback to local Room data
            repository.getWeatherForCity(city)
        }
    }
}