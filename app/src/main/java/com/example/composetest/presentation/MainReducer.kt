package com.example.composetest.presentation

import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardState
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardListReducer
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardListState
import javax.inject.Inject

class MainReducer @Inject constructor(
    val expandableCountryCardListReducer: ExpandableCountryCardListReducer,
) : Reducer<MainState, MainAction> {

    override fun invoke(state: MainState, action: MainAction): MainState {
        return when (action) {
            is MainAction.LoadedCountries -> {
                state.copy(
                    countryListState = ExpandableCountryCardListState(
                        childrenStates = action.countries
                            .map { country ->
                                ExpandableCountryCardState(country = country)
                            }
                    )
                )
            }
            is MainAction.CardListAction -> {
                state.copy(
                    countryListState = expandableCountryCardListReducer(
                        state.countryListState,
                        action = action.cardListAction
                    )
                )
            }
            MainAction.ViewAction.ResumeActivity -> state
        }
    }
}