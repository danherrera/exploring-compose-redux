package com.example.composetest.data

import com.example.composetest.data.network.usecase.GetAllCountriesFromNetworkUseCaseImpl
import com.example.composetest.data.storage.usecase.GetAllCountriesFromStorageUseCaseImpl
import com.example.composetest.data.storage.usecase.SaveCountriesToStorageUseCaseImpl
import com.example.composetest.domain.usecase.GetAllCountriesFromNetworkUseCase
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageUseCase
import com.example.composetest.domain.usecase.SaveCountriesToStorageUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindGetAllCountriesFromNetworkUseCase(useCase: GetAllCountriesFromNetworkUseCaseImpl): GetAllCountriesFromNetworkUseCase

    @Binds
    abstract fun bindGetAllCountriesFromStorageUseCase(useCase: GetAllCountriesFromStorageUseCaseImpl): GetAllCountriesFromStorageUseCase

    @Binds
    abstract fun bindSaveCountriesToStorageUseCase(useCase: SaveCountriesToStorageUseCaseImpl): SaveCountriesToStorageUseCase
}