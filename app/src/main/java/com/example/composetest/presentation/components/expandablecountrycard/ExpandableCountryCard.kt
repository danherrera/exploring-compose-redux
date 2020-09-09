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
import androidx.ui.tooling.preview.Preview
import com.example.composetest.domain.model.Country
import com.example.composetest.domain.model.CountryCode
import com.example.composetest.presentation.Dispatch
import com.example.composetest.presentation.ui.ComposeTestTheme

@Composable
fun ExpandableCountryCard(
    state: ExpandableCountryCardState,
    dispatch: Dispatch<ExpandableCountryCardAction>,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable(onClick = { dispatch(ExpandableCountryCardAction.ToggleExpandCollapse) })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                state.country.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit.Sp(18),
            )
            Text(
                state.country.capital,
                fontWeight = FontWeight.Light,
                fontSize = TextUnit.Sp(14),
            )
            if (state.expanded) {
                Text(
                    state.country.population.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit.Sp(14),
                )
            }
        }
    }
}

@Preview
@Composable
fun CollapsedCardPreview() {
    ComposeTestTheme {
        ExpandableCountryCard(
            state = ExpandableCountryCardState(
                country = Country(
                    CountryCode("USA"),
                    "United States of America",
                    "Washington, DC",
                    331_000_000,
                    flagUrl = "",
                    borders = emptyList()
                )
            ),
            dispatch = {},
        )
    }
}

@Preview
@Composable
fun ExpandedCardPreview() {
    ComposeTestTheme {
        ExpandableCountryCard(
            state = ExpandableCountryCardState(
                country = Country(
                    CountryCode("USA"),
                    "United States of America",
                    "Washington, DC",
                    331_000_000,
                    flagUrl = "",
                    borders = emptyList()
                ),
                expanded = true,
            ),
            dispatch = {},
        )
    }
}
