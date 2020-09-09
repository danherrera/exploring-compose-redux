package com.example.composetest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardList
import com.example.composetest.presentation.ui.ComposeTestTheme

@Composable
fun MainScreen(
    state: MainState,
    dispatch: Dispatch<MainAction>,
) {
    ComposeTestTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                ExpandableCountryCardList(
                    state = state.countryListState,
                    dispatch = { dispatch(MainAction.CardListAction(it)) }
                )
            }
        }
    }
}