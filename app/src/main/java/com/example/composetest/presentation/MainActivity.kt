package com.example.composetest.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.lifecycleScope
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageOrNetworkUseCase
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardList
import com.example.composetest.presentation.ui.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getAllCountriesFromStorageOrNetworkUseCase: GetAllCountriesFromStorageOrNetworkUseCase

    @Inject
    lateinit var reducer: MainReducer

    private object Main : StateAction<MainState, MainAction>

    private lateinit var dispatchAction: Dispatch<MainAction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (state, action) = Main.Redux(
                initialState = MainState(),
                reducer = reducer,
            )
            dispatchAction = action

            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        ExpandableCountryCardList(
                            state = state.countryListState,
                            action = { action(MainAction.CardListAction(it)) }
                        )
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.IO) { getAllCountriesFromStorageOrNetworkUseCase() }
                .fold({
                    Log.e("MainActivity", "Failed to get countries", it)
                    dispatchAction(MainAction.LoadedCountries(emptyList()))
                }, { countries ->
                    dispatchAction(MainAction.LoadedCountries(countries))
                })
        }

    }
}

