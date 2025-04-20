/**
 * @author Mohamed Naser.
 */
package com.project.forecast.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.project.forecast.ForecastUiModel
import com.project.weather_utils.getWeatherIconUrl

@Composable
fun ForecastItem(forecast: ForecastUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(getWeatherIconUrl(forecast.iconUrl)),
            contentDescription = forecast.condition,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(forecast.date)
            Text(forecast.temperature)
            Text(forecast.condition)
        }
    }
}