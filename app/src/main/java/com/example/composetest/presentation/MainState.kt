package com.example.composetest.presentation

import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardListState

data class MainState(
    val countryListState: ExpandableCountryCardListState = ExpandableCountryCardListState()
)