package com.project.features_cityinput

data class CityInputState(
    val city: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
    val isValid: Boolean
        get() = city.length >= 2
}