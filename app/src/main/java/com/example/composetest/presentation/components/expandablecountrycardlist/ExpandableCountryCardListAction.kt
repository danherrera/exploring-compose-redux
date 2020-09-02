package com.example.composetest.presentation.components.expandablecountrycardlist

import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardAction


sealed class ExpandableCountryCardListAction(val index: Int) {
    data class ExpandableCardAction(
        val childIndex: Int,
        val cardAction: ExpandableCountryCardAction,
    ): ExpandableCountryCardListAction(childIndex)
}