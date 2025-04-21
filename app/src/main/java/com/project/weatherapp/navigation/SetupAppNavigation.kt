/**
 * @author Mohamed Naser.
 */
package com.project.weatherapp.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import com.project.currentweather.CurrentWeatherScreen
import com.project.feature.currentweather.CurrentWeatherViewModel
import com.project.features_cityinput.CityInputScreen
import com.project.features_cityinput.CityInputViewModel
import com.project.forecast.ForecastScreen
import com.project.forecast.ForecastViewModel

@Composable
fun SetupAppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.CityInputScreen
    ) {

        composable<Screen.CityInputScreen> {
            val viewModel: CityInputViewModel = hiltViewModel()
            CityInputScreen(
                onCitySubmitted = { city ->
                    navController.navigate(Screen.CurrentWeatherScreen(city))
                },
                viewModel = viewModel
            )
        }

        composable<Screen.CurrentWeatherScreen> {
            val viewModel: CurrentWeatherViewModel = hiltViewModel()
            val args = it.toRoute<Screen.CurrentWeatherScreen>()
            CurrentWeatherScreen(
                city = args.city,
                onShowForecast = { city ->
                    navController.navigate(Screen.ForecastScreen(city))
                },
                viewModel = viewModel
            )
        }

        composable<Screen.ForecastScreen> {
            val viewModel: ForecastViewModel = hiltViewModel()
            val args = it.toRoute<Screen.ForecastScreen>()
            ForecastScreen(
                city = args.city,
                viewModel = viewModel
            )
        }
    }
}