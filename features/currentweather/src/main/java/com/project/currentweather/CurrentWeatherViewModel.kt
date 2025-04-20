/**
 * @author Mohamed Naser.
 */
package com.project.feature.currentweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.currentweather.CurrentWeatherState
import com.project.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CurrentWeatherState())
    val state = _state.asStateFlow()


    fun loadWeather(city: String) {
        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            runCatching { getCurrentWeatherUseCase(city) }
                .onSuccess { result ->
                    val weather = result.getOrNull()
                    _state.update {
                        it.copy(
                            isLoading = false,
                            weather = weather,
                            error = null
                        )
                    }
                }
                .onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            weather = null,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
        }
    }
    fun refresh(city: String) {
        loadWeather(city)
    }
}