package com.example.composetest.data.storage.usecase

import arrow.core.Either
import com.example.composetest.data.storage.country.CountryDao
import com.example.composetest.data.storage.country.CountryEntity
import com.example.composetest.domain.usecase.SaveCountriesToStorageUseCase
import javax.inject.Inject

class SaveCountriesToStorageUseCaseImpl @Inject constructor(
    private val countryDao: CountryDao
) : SaveCountriesToStorageUseCase {

    override suspend fun invoke(input: SaveCountriesToStorageUseCase.Request?): Either<Throwable, Unit> =
        Either.catch {
            val request = input ?: SaveCountriesToStorageUseCase.Request()
            countryDao.insertAll(request.countries.map {
                CountryEntity(
                    it.code.code,
                    it.name,
                    it.capital,
                    it.population,
                    it.flagUrl,
                    it.borders.joinToString { b -> b.code },
                )
            })
        }
}