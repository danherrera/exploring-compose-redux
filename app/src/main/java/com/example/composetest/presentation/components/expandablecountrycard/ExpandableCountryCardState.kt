package com.example.composetest.presentation.components.expandablecountrycard

import com.example.composetest.domain.model.Country

data class ExpandableCountryCardState(
    val country: Country,
    val expanded: Boolean = false,
)

