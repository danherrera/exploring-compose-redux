package com.example.composetest.presentation.components.expandablecountrycard

sealed class ExpandableCountryCardAction {
    object ExpandCard : ExpandableCountryCardAction()
    object CollapseCard : ExpandableCountryCardAction()
    object ToggleExpandCollapse : ExpandableCountryCardAction()
}

