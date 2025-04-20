/**
 * @author Mohamed Naser.
 */

package com.project.domain.usecase

import com.project.domain.repository.WeatherRepository
import javax.inject.Inject

class GetLastSearchedCitiesUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.getLastSearchedCities()
}