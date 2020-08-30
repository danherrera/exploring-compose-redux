package com.example.composetest.domain.model

data class Country(
    val code: CountryCode,
    val name: String,
    val capital: String,
    val population: Long,
    val flagUrl: String,
    val borders: List<CountryCode>,
)