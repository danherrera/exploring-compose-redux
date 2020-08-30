package com.example.composetest.data.network.restcountries

import arrow.core.Either
import retrofit2.http.GET

interface RestCountriesApi {

    @GET("all")
    suspend fun getAllCountries(): Either<Throwable, List<CountryDto>>
}
