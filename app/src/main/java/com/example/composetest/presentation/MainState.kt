package com.example.composetest.presentation

import com.example.composetest.domain.model.Country

data class MainState(
    val countryList: List<Country> = emptyList(),
)