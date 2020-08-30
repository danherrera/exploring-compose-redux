package com.example.composetest.data.storage.usecase

import arrow.core.Either
import com.example.composetest.data.storage.country.CountryDao
import com.example.composetest.domain.model.Country
import com.example.composetest.domain.model.CountryCode
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageUseCase
import javax.inject.Inject

class GetAllCountriesFromStorageUseCaseImpl @Inject constructor(
    private val countryDao: CountryDao
) : GetAllCountriesFromStorageUseCase {

    override suspend fun invoke(input: Unit?): Either<Throwable, List<Country>> = Either.catch {
        countryDao.getAll().let { countries ->
            countries.map {
                Country(
                    CountryCode(it.code),
                    it.name,
                    it.capital,
                    it.population,
                    it.flagUrl,
                    it.borders.split(",").map { b -> CountryCode(b) }
                )
            }
        }
    }
}