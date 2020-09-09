package com.example.composetest.presentation

import com.example.composetest.domain.model.Country
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardListAction

sealed class MainAction {
    sealed class ViewAction : MainAction() {
        object ResumeActivity : ViewAction()
    }
    data class LoadedCountries(val countries: List<Country>) : MainAction()
    data class CardListAction(val cardListAction: ExpandableCountryCardListAction) : MainAction()
}