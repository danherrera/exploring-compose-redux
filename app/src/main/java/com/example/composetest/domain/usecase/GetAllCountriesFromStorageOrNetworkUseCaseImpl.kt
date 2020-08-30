package com.example.composetest.domain.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.composetest.domain.model.Country
import javax.inject.Inject

class GetAllCountriesFromStorageOrNetworkUseCaseImpl @Inject constructor(
    private val getAllCountriesFromNetworkUseCase: GetAllCountriesFromNetworkUseCase,
    private val getAllCountriesFromStorageUseCase: GetAllCountriesFromStorageUseCase,
    private val saveCountriesToStorageUseCase: SaveCountriesToStorageUseCase,
) : GetAllCountriesFromStorageOrNetworkUseCase {

    override suspend fun invoke(input: GetAllCountriesFromStorageOrNetworkUseCase.Request?): Either<Throwable, List<Country>> {
        val request = input ?: GetAllCountriesFromStorageOrNetworkUseCase.Request()
        return if (request.forceNetwork) {
            getFromNetworkAndSave()
        } else {
            getAllCountriesFromStorageUseCase()
                .fold({ it.left() }, {
                    if (it.isEmpty()) getFromNetworkAndSave()
                    else it.right()
                })
        }
    }

    private suspend fun getFromNetworkAndSave(): Either<Throwable, List<Country>> {
        return getAllCountriesFromNetworkUseCase()
            .fold({ it.left() }, { countries ->
                saveCountriesToStorageUseCase(SaveCountriesToStorageUseCase.Request(countries))
                    .fold({
                        it.left()
                    }, {
                        countries.right()
                    })
            })
    }
}