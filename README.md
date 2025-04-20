# 🌤 Weather Now & Later

**Weather Now & Later** is a modern, modular Android application built with Jetpack Compose that allows users to view the current weather and a 5-day forecast for any city using the [OpenWeatherMap API](https://openweathermap.org/api). The app follows Clean Architecture principles, uses MVVM/MVI patterns, and stores the last searched city for convenience.

---

## 🚀 Features

✅ Search and select any city  
✅ View current weather (temperature, condition, icon)  
✅ See a 5-day forecast in a list  
✅ Last searched city auto-loaded on app reopen  
✅ Swipe-to-refresh support  
✅ Responsive and clean UI using Jetpack Compose  

---

## 🧱 Tech Stack

- **Kotlin + Jetpack Compose**
- **MVVM (City Input, Current Weather)**  
- **MVI (5-Day Forecast)**  
- **Clean Architecture (Presentation → Domain → Data)**  
- **Retrofit + OkHttp + Gson** (network)
- **Room** (local cache)
- **DataStore** (store last searched city)
- **Dagger-Hilt** (Dependency Injection)
- **Modularization**
- **Custom shared utils** published to local Maven

---

## 📦 Project Structure

```
WeatherNowAndLater/
├── app/                   # Main app module, sets up navigation and theme
├── core/                  # Shared DI, networking, navigation, etc.
├── data/                  # Repository, Room, DataStore, API
├── domain/                # Use cases, domain models, repository contracts
├── features/
│   ├── cityinput/         # Input screen (MVVM)
│   ├── currentweather/    # Current weather display (MVVM)
│   └── forecast/          # Forecast list screen (MVI)
└── weather-utils/         # Shared utilities like TemperatureFormatter, DateFormatter
```

---

## 🛠️ Architecture

This app follows **Clean Architecture** with these layers:

- **Presentation:** UI and ViewModel
- **Domain:** Use cases and interfaces
- **Data:** Remote and local sources, repository implementation

---


## 🧩 Modularity

Each feature is isolated in its own Gradle module to improve maintainability, scalability, and build times.

```
features/
│
├── cityinput/
├── currentweather/
└── forecast/
```

---

## 🏗️ Custom Library

`weather-utils` module is published to local Maven and used in `core` and `features`.

Tools included:

- `TemperatureFormatter.kt`
- `DateFormatter.kt`
- `WeatherIconMapper.kt`

> 📦 Published locally via:
```bash
./gradlew :weather-utils:publish
```

---

## 🧠 Navigation

Uses `Navigation Compose` with Kotlin serialization for type-safe navigation.

```kotlin
@Serializable
data class ForecastScreen(val city: String)
```

---

## 🔐 Secrets

> You must have a valid OpenWeatherMap API key.

In your `local.properties`:

```properties
OPEN_WEATHER_API_KEY=your_api_key_here
```

---

## 🚀 Getting Started

1. Clone the project:
   ```bash
   git clone https://github.com/yourusername/WeatherNowAndLater.git
   cd WeatherNowAndLater
   ```

2. Add your API key to `local.properties`.

3. Run the app via Android Studio or:
   ```bash
   ./gradlew installDebug
   ```

---

## 👨‍💻 Author

**Mohamed Naser**  
Android & Kotlin Multiplatform Developer  
[LinkedIn](https://linkedin.com/in/mahmmedn19)

---
