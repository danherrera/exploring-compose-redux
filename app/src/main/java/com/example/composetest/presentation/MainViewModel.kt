package com.example.composetest.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.domain.usecase.GetAllCountriesFromStorageOrNetworkUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(
    private val getAllCountriesFromStorageOrNetworkUseCase: GetAllCountriesFromStorageOrNetworkUseCase,
    val reducer: MainReducer,
) : ViewModel() {

    private val _actionEvents = MutableLiveDataEvent<MainAction>()
    val actionEvents: LiveDataEvent<MainAction>
        get() = _actionEvents

    fun getCountries(forceNetwork: Boolean = false) = viewModelScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            getAllCountriesFromStorageOrNetworkUseCase(
                GetAllCountriesFromStorageOrNetworkUseCase.Request(forceNetwork)
            )
        }
            .fold({
                _actionEvents.setEvent(MainAction.LoadedCountries(emptyList()))
            }, {
                _actionEvents.setEvent(MainAction.LoadedCountries(it))
            })
    }
}