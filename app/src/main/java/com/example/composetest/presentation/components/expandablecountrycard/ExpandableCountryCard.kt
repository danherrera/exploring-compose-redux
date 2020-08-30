package com.example.composetest.presentation.components.expandablecountrycard

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.composetest.domain.model.Country
import com.example.composetest.presentation.Reducer
import com.example.composetest.presentation.redux

@Composable
fun ExpandableCountryCard(
    country: Country,
    reducer: Reducer<ExpandableCountryCardState, ExpandableCountryCardAction> = ExpandableCountryCardReducer,
    initialState: ExpandableCountryCardState = ExpandableCountryCardState(),
) {
    val (state, action) = redux(
        initialState = initialState,
        reducer = reducer,
    )
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable(onClick = { action(ExpandableCountryCardAction.ToggleExpandCollapse) })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                country.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit.Sp(18),
            )
            Text(
                country.capital,
                fontWeight = FontWeight.Light,
                fontSize = TextUnit.Sp(14),
            )
            if (state.expanded) {
                Text(
                    country.population.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit.Sp(14),
                )
            }
        }
    }
}
