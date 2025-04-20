/**
 * @author Mohamed Naser.
 */
package com.project.data.mapper

import com.project.data.local.database.WeatherEntity
import com.project.data.remote.dto.CurrentWeatherDto
import com.project.data.remote.dto.ForecastResponseDto
import com.project.domain.model.CurrentWeather
import com.project.domain.model.DailyForecast

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    val weatherInfo = weather.firstOrNull()
    return CurrentWeather(
        city = cityName,
        country = sys.country,
        temperature = main.temp,
        condition = weatherInfo?.main.orEmpty(),
        icon = weatherInfo?.icon.orEmpty(),
        windSpeed = wind.speed,
        humidity = main.humidity,
        feelsLike = main.feelsLike,
        sunrise = sys.sunrise,
        sunset = sys.sunset,
        timestamp = timestamp
    )
}

fun ForecastResponseDto.toDailyForecast(): List<DailyForecast> {
    return list
        .groupBy { it.dateText.substring(0, 10) }
        .map { (date, entries) ->
            val avgTemp = entries.map { it.main.temp }.average()
            val representativeWeather = entries.firstOrNull()?.weather?.firstOrNull()
            DailyForecast(
                date = date,
                avgTemp = avgTemp,
                icon = representativeWeather?.icon ?: "01d",
                condition = representativeWeather?.main ?: "Clear"
            )
        }
}

fun CurrentWeather.toEntity(): WeatherEntity {
    return WeatherEntity(
        cityName = city,
        country = country,
        temperature = temperature,
        condition = condition,
        icon = icon,
        windSpeed = windSpeed,
        humidity = humidity,
        feelsLike = feelsLike,
        sunrise = sunrise,
        sunset = sunset,
    )
}

fun WeatherEntity.toDomain(): CurrentWeather {
    return CurrentWeather(
        city = cityName,
        country = country,
        temperature = temperature,
        condition = condition,
        icon = icon,
        windSpeed = windSpeed,
        humidity = humidity,
        feelsLike = feelsLike,
        sunrise = sunrise,
        sunset = sunset,
        timestamp = updatedAt
    )
}