package com.piyushhood.tmdbrowser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushhood.tmdbrowser.domain.model.ThemeMode
import com.piyushhood.tmdbrowser.domain.repository.PreferencesRepository
import com.piyushhood.tmdbrowser.presentation.state.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PreferencesViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val themeModeState : StateFlow<UiState<ThemeMode>> =
        preferencesRepository.themeMode
            .map<ThemeMode , UiState<ThemeMode>> { themeMode ->
                UiState.Success(themeMode)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Loading
            )

    fun updateThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch {
            preferencesRepository.setThemeMode(themeMode)
        }
    }
}