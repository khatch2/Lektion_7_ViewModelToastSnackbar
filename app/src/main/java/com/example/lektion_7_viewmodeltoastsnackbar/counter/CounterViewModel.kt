package com.example.lektion_7_viewmodeltoastsnackbar.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    // Setup mutableStateFlow
    // LiveData

    // Expose UI to Screen
    private val _state = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _state.asStateFlow()

    fun add() {

        _state.update {
            index -> index.copy(

                // TODO - Test with: += and ++
                counterValue = index.counterValue + 1

            )
        }

    }

}












