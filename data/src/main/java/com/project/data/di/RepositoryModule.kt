/**
 * @author Mohamed Naser.
 */

package com.project.data.di

import com.project.data.local.data_store.SearchHistoryManager
import com.project.data.local.database.WeatherDao
import com.project.data.local.database.WeatherDatabase
import com.project.data.remote.network.WeatherApiService
import com.project.data.repository.WeatherRepositoryImpl
import com.project.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(
        api: WeatherApiService,
        dao: WeatherDao,
        dataStore: SearchHistoryManager
    ): WeatherRepository = WeatherRepositoryImpl(api, dao, dataStore)
}