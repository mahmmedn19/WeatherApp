/**
 * @author Mohamed Naser.
 */
package com.project.weatherapp.navigation


import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object CityInputScreen : Screen

    @Serializable
    data class CurrentWeatherScreen(val city: String) : Screen

    @Serializable
    data class ForecastScreen(val city: String) : Screen
}