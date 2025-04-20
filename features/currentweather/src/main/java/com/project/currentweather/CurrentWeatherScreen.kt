/**
 * @author Mohamed Naser.
 */
package com.project.currentweather

import androidx.compose.foundation.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.feature.currentweather.CurrentWeatherViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.project.weather_utils.TemperatureFormatter
import com.project.weather_utils.getWeatherIconUrl

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentWeatherScreen(
    city: String,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(city) {
        viewModel.loadWeather(city)
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.refresh(city) }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            CurrentWeatherContent(state = state)
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun CurrentWeatherContent(state: CurrentWeatherState) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(text = state.error, color = MaterialTheme.colorScheme.error)
            }

            state.weather != null -> {
                val weather = state.weather

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = weather.city, style = MaterialTheme.typography.headlineMedium)

                    Spacer(modifier = Modifier.height(16.dp))


                    Image(
                        painter = rememberAsyncImagePainter(getWeatherIconUrl(weather.icon)),
                        contentDescription = weather.condition
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = TemperatureFormatter.formatFromKelvin(weather.temperature),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(text = weather.condition)
                }
            }
        }
    }
}