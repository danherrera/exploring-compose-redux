package com.example.composetest.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.example.composetest.presentation.components.expandablecountrycardlist.ExpandableCountryCardList
import com.example.composetest.presentation.ui.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private object Main : StateAction<MainState, MainAction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (state, action) = Main.Redux(
                initialState = MainState(),
                reducer = viewModel.reducer,
            )
            viewModel.actionEvents.observeEvents(this, action)

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

        viewModel.getCountries()
    }
}

