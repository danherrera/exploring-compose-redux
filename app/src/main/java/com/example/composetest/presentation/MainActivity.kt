package com.example.composetest.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import com.example.composetest.domain.model.Country
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageOrNetworkUseCase
import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCard
import com.example.composetest.presentation.components.expandablecountrycard.ExpandableCountryCardReducer
import com.example.composetest.presentation.ui.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getAllCountriesFromStorageOrNetworkUseCase: GetAllCountriesFromStorageOrNetworkUseCase
    private object Main : StateAction<MainState, MainAction>
    private val reducer = Main.Reducer { state, action ->
        when (action) {
            is MainAction.LoadedCountries -> state.copy(countryList = action.countries)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (state, action) = Main.Redux(
                initialState = MainState(),
                reducer = reducer,
            )

            lifecycleScope.launchWhenCreated {
                withContext(Dispatchers.IO) { getAllCountriesFromStorageOrNetworkUseCase() }
                    .fold({
                        Log.e("MainActivity", "Failed to get countries", it)
                        action(MainAction.LoadedCountries(emptyList()))
                    }, { countries ->
                        action(MainAction.LoadedCountries(countries))
                    })
            }

            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting("Android")
                        CountryCardsList(state.countryList)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun CountryCardsList(countries: List<Country>) {
    ScrollableColumn {
        countries.forEach {
            ExpandableCountryCard(country = it)
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                country.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit.Companion.Sp(18),
            )
            Text(
                country.capital,
                fontWeight = FontWeight.Light,
                fontSize = TextUnit.Companion.Sp(14),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        Greeting("Android")
    }
}