/**
 * @author Mohamed Naser.
 */
package com.project.weather_utils

fun getWeatherIconUrl(iconCode: String): String {
    return "https://openweathermap.org/img/wn/${iconCode}@2x.png"
}