package com.project.features_cityinput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.features_cityinput.component.CityTextField

@Composable
fun CityInputScreen(
    onCitySubmitted: (String) -> Unit,
    viewModel: CityInputViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val history by viewModel.searchHistory.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadSearchHistory()
    }

    CityInputContent(
        state = state,
        history = history,
        onCityChanged = viewModel::onCityChange,
        onSubmitClicked = {
            viewModel.submitCity()
            if (state.isValid && state.errorMessage == null) {
                onCitySubmitted(state.city)
            }
        },
        onHistoryItemClicked = viewModel::onCityChange
    )
}

@Composable
fun CityInputContent(
    state: CityInputState,
    history: List<String>,
    onCityChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    onHistoryItemClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CityTextField(
            value = state.city,
            isError = !state.isValid,
            onValueChange = onCityChanged
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSubmitClicked,
            enabled = state.isValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Weather")
        }

        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        if (history.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Recent Searches:",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
            history.forEach { city ->
                TextButton(onClick = { onHistoryItemClicked(city) }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = city,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "No recent searches",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}