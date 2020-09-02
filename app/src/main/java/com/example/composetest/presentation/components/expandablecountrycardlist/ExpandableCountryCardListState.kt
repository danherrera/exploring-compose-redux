package com.example.composetest.presentation.components.expandablecountrycardlist

import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardState

data class ExpandableCountryCardListState(
    val childrenStates: List<ExpandableCountryCardState> = emptyList()
)