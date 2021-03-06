package com.example.composetest.presentation.components.expandablecountrycard

import com.example.composetest.presentation.Reducer
import javax.inject.Inject

class ExpandableCountryCardReducer @Inject constructor() :
    Reducer<ExpandableCountryCardState, ExpandableCountryCardAction> {

    override fun invoke(
        state: ExpandableCountryCardState,
        action: ExpandableCountryCardAction
    ): ExpandableCountryCardState {
        return when (action) {
            ExpandableCountryCardAction.ExpandCard -> state.copy(expanded = true)
            ExpandableCountryCardAction.CollapseCard -> state.copy(expanded = false)
            ExpandableCountryCardAction.ToggleExpandCollapse -> state.copy(expanded = !state.expanded)
        }
    }
}
