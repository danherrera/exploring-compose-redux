package com.example.composetest.data.network.restcountries

data class CountryDto(
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val capital: String,
    val region: String,
    val subregion: String,
    val population: Long,
    val area: Double,
    val borders: List<String>,
    val flag: String,
    val languages: List<LanguageDto>,
    val currencies: List<CurrencyDto>,
)