package com.example.shoulderphysicaltherapy

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ExerciseViewModel(private val state: SavedStateHandle) : ViewModel() {
    companion object {
        private const val OPTIONS_KEY = "options_key"
        private const val SELECTED_OPTION_KEY = "selected_option_key"
        //Add a key (above) for saving the selected option
        private val defaultOptions = listOf("Set 1", "Set 2", "Set 3", "Set 4")
    }

    var options: List<String>
        get() = state.get<List<String>>(OPTIONS_KEY) ?: defaultOptions
        set(value) {
            state[OPTIONS_KEY] = value
        }

    // Use MutableState for selectedOption, but also save and restore it from SavedStateHandle
    var selectedOption = mutableStateOf<String?>(state.get(SELECTED_OPTION_KEY))
        private set // Make the setter private to restrict modifications from outside the ViewModel

    fun updateSelectedOption(option: String) {
        selectedOption.value = option
        state[SELECTED_OPTION_KEY] = option  // Save the selected option in SavedStateHandle
    }

    fun clearSelectedOption() {
        selectedOption.value = null // Resetting to null or to a default value indicating no selection
    }
}




