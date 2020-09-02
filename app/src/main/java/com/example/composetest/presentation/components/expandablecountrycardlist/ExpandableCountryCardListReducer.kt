package com.example.composetest.presentation.components.expandablecountrycardlist

import com.example.composetest.presentation.Reducer
import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardReducer
import com.example.composetest.presentation.replaceIndexWith
import javax.inject.Inject

class ExpandableCountryCardListReducer @Inject constructor(
    private val expandableCountryCardReducer: ExpandableCountryCardReducer,
) : Reducer<ExpandableCountryCardListState, ExpandableCountryCardListAction> {

    override fun invoke(
        state: ExpandableCountryCardListState,
        action: ExpandableCountryCardListAction
    ): ExpandableCountryCardListState {
        return when (action) {
            is ExpandableCountryCardListAction.ExpandableCardAction -> {
                val childState = state.childrenStates[action.index]
                val newChildState = expandableCountryCardReducer(childState, action.cardAction)
                state.copy(
                    childrenStates = state.childrenStates.replaceIndexWith(
                        action.index,
                        newChildState
                    )
                )
            }
        }
    }
}