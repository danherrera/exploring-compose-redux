package com.example.composetest.presentation.components.expandablecountrycardlist

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.composetest.domain.model.Country
import com.example.composetest.domain.model.CountryCode
import com.example.composetest.presentation.Dispatch
import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCard
import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardState
import com.example.composetest.presentation.ui.ComposeTestTheme

@Composable
fun ExpandableCountryCardList(
    state: ExpandableCountryCardListState,
    dispatch: Dispatch<ExpandableCountryCardListAction>,
) {
    ScrollableColumn {
        state.childrenStates.forEachIndexed { index, childState ->
            ExpandableCountryCard(
                state = childState,
                dispatch = { childAction ->
                    dispatch(
                        ExpandableCountryCardListAction.ExpandableCardAction(
                            index,
                            childAction
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardList() {
    ComposeTestTheme {
        ExpandableCountryCardList(
            state = ExpandableCountryCardListState(
                childrenStates = listOf(
                    Country(
                        CountryCode("USA"),
                        "United States of America",
                        "Washington, DC",
                        331_000_000,
                        flagUrl = "",
                        borders = listOf(
                            CountryCode("CAN"),
                            CountryCode("MEX"),
                        )
                    ),
                    Country(
                        CountryCode("CAN"),
                        "Canada",
                        "Ottawa",
                        38_000_000,
                        flagUrl = "",
                        borders = listOf(
                            CountryCode("USA"),
                        )
                    ),
                    Country(
                        CountryCode("MEX"),
                        "Mexico",
                        "Mexico City",
                        126_000_000,
                        flagUrl = "",
                        borders = listOf(
                            CountryCode("USA"),
                            CountryCode("GUA"),
                            CountryCode("BEL"),
                        )
                    ),
                )
                    .map { ExpandableCountryCardState(it) },
            ),
            dispatch = {},
        )
    }
}