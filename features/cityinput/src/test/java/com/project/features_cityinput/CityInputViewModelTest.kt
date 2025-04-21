package com.project.features_cityinput

import com.project.domain.model.CurrentWeather
import com.project.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class CityInputViewModelTest {

    private lateinit var viewModel: CityInputViewModel
    private lateinit var useCase: GetCurrentWeatherUseCase

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = mock()
        viewModel = CityInputViewModel(useCase)
    }

    private fun createMockWeather(city: String) = CurrentWeather(
        city = city,
        country = "EG",
        temperature = 25.0,
        condition = "Clear",
        icon = "01d",
        windSpeed = 3.5,
        humidity = 20,
        feelsLike = 24.0,
        sunrise = 1683531600,
        sunset = 1683582000,
        timestamp = System.currentTimeMillis()
    )

    @Test
    fun `getState initial value`() = runTest {
        val state = viewModel.state.value
        assertEquals("", state.city)
        assertFalse(state.isValid)
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @Test
    fun `onCityChange updates city in state`() = runTest {
        viewModel.onCityChange("Cairo")
        val state = viewModel.state.value
        assertEquals("Cairo", state.city)
        assertTrue(state.isValid)
    }

    @Test
    fun `submitCity success updates state`() = runTest {
        val city = "Cairo"
        val weather = createMockWeather(city)
        whenever(useCase(city)).thenReturn(Result.success(weather))

        viewModel.onCityChange(city)
        viewModel.submitCity()

        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertNull(state.errorMessage)
    }

    @Test
    fun `submitCity failure updates state`() = runTest {
        val city = "Nowhere"
        whenever(useCase(city)).thenReturn(Result.failure(Exception("City not found")))

        viewModel.onCityChange(city)
        viewModel.submitCity()

        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertEquals("City not found", state.errorMessage)
    }

    @Test
    fun `submitCity trims city input`() = runTest {
        val city = "  Cairo  "
        val trimmed = "Cairo"
        val weather = createMockWeather(trimmed)
        whenever(useCase(trimmed)).thenReturn(Result.success(weather))

        viewModel.onCityChange(city)
        viewModel.submitCity()

        advanceUntilIdle()

        verify(useCase).invoke(trimmed)
    }


    @Test
    fun `submitCity during existing call`() = runTest {
        val city = "Cairo"
        val weather = createMockWeather(city)
        whenever(useCase(city)).thenReturn(Result.success(weather))

        viewModel.onCityChange(city)
        viewModel.submitCity()
        viewModel.submitCity() // Called again while loading

        advanceUntilIdle()

        val state = viewModel.state.value
        assertFalse(state.isLoading) // eventually should be false
        assertNull(state.errorMessage)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
