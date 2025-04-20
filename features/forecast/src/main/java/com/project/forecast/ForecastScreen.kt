package com.project.forecast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.forecast.component.ForecastItem

@Composable
fun ForecastScreen(
    city: String,
    viewModel: ForecastViewModel
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(city) {
        viewModel.handleIntent(ForecastIntent.LoadForecast(city))
    }

    ForecastContent(state = state)
}

@Composable
fun ForecastContent(state: ForecastState) {
    Box(Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(state.forecasts) { item ->
                        ForecastItem(item)
                    }
                }
            }
        }
    }
}
