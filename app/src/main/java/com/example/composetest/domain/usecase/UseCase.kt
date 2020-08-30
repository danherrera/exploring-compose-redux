package com.example.composetest.domain.usecase

import arrow.core.Either
import com.example.composetest.domain.model.Country

interface UseCase<I, O> {
    suspend operator fun invoke(input: I? = null): Either<Throwable, O>
}

interface GetAllCountriesFromNetworkUseCase : UseCase<Unit, List<Country>>

interface GetAllCountriesFromStorageUseCase : UseCase<Unit, List<Country>>

interface SaveCountriesToStorageUseCase : UseCase<SaveCountriesToStorageUseCase.Request, Unit> {
    data class Request(val countries: List<Country> = emptyList())
}

interface GetAllCountriesFromStorageOrNetworkUseCase
    : UseCase<GetAllCountriesFromStorageOrNetworkUseCase.Request, List<Country>> {
    data class Request(val forceNetwork: Boolean = false)
}