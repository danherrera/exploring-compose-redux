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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (state, dispatch) = redux(
                initialState = MainState(),
                reducer = viewModel.reducer,
                middlewares = viewModel.middlewares,
            )
            viewModel.actionEvents.observeEvents(this, dispatch)

            MainScreen(state = state, dispatch = dispatch)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.viewAction(MainAction.ViewAction.ResumeActivity)
    }
}

