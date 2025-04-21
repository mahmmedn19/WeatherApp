/**
 * @author Mohamed Naser.
 */

package com.project.features_cityinput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CityInputState())
    val state = _state.asStateFlow()

    fun onCityChange(city: String) {
        _state.update { it.copy(city = city) }
    }

    fun submitCity() {
        val currentCity = _state.value.city.trim()
        if (currentCity.isEmpty()) {
            _state.update { it.copy(errorMessage = "Please enter a valid city name") }
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = getCurrentWeatherUseCase(currentCity)
            result.onSuccess {
                _state.update { it.copy(isLoading = false) }
            }.onFailure {
                _state.update {
                    it.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
                }
            }
        }
    }
}