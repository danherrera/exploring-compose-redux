package com.example.composetest.domain

import com.example.composetest.domain.usecase.GetAllCountriesFromStorageOrNetworkUseCase
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageOrNetworkUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetAllCountriesFromStorageOrNetworkUseCase(useCase: GetAllCountriesFromStorageOrNetworkUseCaseImpl): GetAllCountriesFromStorageOrNetworkUseCase
}