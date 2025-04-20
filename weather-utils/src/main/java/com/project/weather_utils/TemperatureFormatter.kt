package com.project.weather_utils

import java.util.Locale


object TemperatureFormatter {

    /**
     * Converts temperature from Kelvin to Celsius and formats it as a string.
     *
     * @param kelvinTemp Temperature in Kelvin.
     * @return Formatted temperature string (e.g., "25°C").
     */
    fun formatFromKelvin(kelvinTemp: Double): String {
        val celsius = kelvinTemp - 273.15
        return String.format(Locale.getDefault(), "%.0f°C", celsius)
    }

    /**
     * Formats a temperature already in Celsius (e.g., from metric units).
     *
     * @param celsiusTemp Temperature in Celsius.
     * @return Formatted temperature string (e.g., "25°C").
     */
    fun formatCelsius(celsiusTemp: Double): String {
        return String.format(Locale.getDefault(), "%.0f°C", celsiusTemp)
    }

    /**
     * Converts temperature from Kelvin to Fahrenheit.
     */
    fun formatFromKelvinToFahrenheit(kelvinTemp: Double): String {
        val fahrenheit = (kelvinTemp - 273.15) * 9 / 5 + 32
        return String.format(Locale.getDefault(), "%.0f°F", fahrenheit)
    }
}