/**
 * @author Mohamed Naser.
 */

package com.project.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.GetForecastUseCase
import com.project.weather_utils.TemperatureFormatter
import com.project.weather_utils.formatDateFromUnix
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.LoadForecast -> loadForecast(intent.city)
            is ForecastIntent.Refresh -> _state.update { it.copy(isLoading = true) }
        }
    }

    private fun loadForecast(city: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = getForecastUseCase(city)
            when {
                result.isSuccess -> {
                    _state.update {
                        it.copy(
                            forecasts = result.getOrThrow().map { forecast ->
                                ForecastUiModel(
                                    date = formatDateFromUnix(forecast.date.toLong()),
                                    temperature = TemperatureFormatter.formatCelsius(forecast.avgTemp),
                                    condition = forecast.condition,
                                    iconUrl = forecast.icon
                                )
                            },
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                result.isFailure -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.exceptionOrNull()?.message
                        )
                    }
                }
            }
        }
    }
}