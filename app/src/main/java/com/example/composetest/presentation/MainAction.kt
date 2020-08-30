package com.example.composetest.presentation

import com.example.composetest.domain.model.Country

sealed class MainAction {
    data class LoadedCountries(val countries: List<Country>) : MainAction()
}