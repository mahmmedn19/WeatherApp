/**
 * @author Mohamed Naser.
 */
package com.project.features_cityinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.features_cityinput.component.CityTextField

@Composable
fun CityInputScreen(
    onCitySubmitted: (String) -> Unit,
    viewModel: CityInputViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CityInputContent(
        state = state,
        onCityChanged = viewModel::onCityChange,
        onSubmitClicked = {
            viewModel.submitCity()
            if (state.isValid && state.errorMessage == null) {
                onCitySubmitted(state.city)
            }
        }
    )
}

@Composable
fun CityInputContent(
    state: CityInputState,
    onCityChanged: (String) -> Unit,
    onSubmitClicked: () -> Unit
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
            enabled = state.isValid && state.errorMessage == null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Weather")
        }

        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}



