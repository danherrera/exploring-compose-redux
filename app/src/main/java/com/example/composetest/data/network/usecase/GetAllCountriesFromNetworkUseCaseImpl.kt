package com.example.composetest.data.network.usecase

import arrow.core.Either
import com.example.composetest.data.network.restcountries.RestCountriesApi
import com.example.composetest.domain.model.Country
import com.example.composetest.domain.model.CountryCode
import com.example.composetest.domain.usecase.GetAllCountriesFromNetworkUseCase
import javax.inject.Inject

class GetAllCountriesFromNetworkUseCaseImpl @Inject constructor(
    private val api: RestCountriesApi
) : GetAllCountriesFromNetworkUseCase {

    override suspend fun invoke(input: Unit?): Either<Throwable, List<Country>> {
        return api.getAllCountries()
            .map { countries ->
                countries.map { c ->
                    Country(
                        CountryCode(c.alpha3Code),
                        c.name,
                        c.capital,
                        c.population,
                        c.flag,
                        c.borders.map { CountryCode(it) })
                }
            }
    }
}