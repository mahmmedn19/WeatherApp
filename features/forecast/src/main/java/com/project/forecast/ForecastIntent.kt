/**
 * @author Mohamed Naser.
 */
package com.project.forecast

sealed interface ForecastIntent {
    data class LoadForecast(val city: String) : ForecastIntent
    data object Refresh : ForecastIntent
}