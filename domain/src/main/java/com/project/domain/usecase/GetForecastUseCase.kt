/**
 * @author Mohamed Naser.
 */

package com.project.domain.usecase

import com.project.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) = repository.getForecast(city)
}