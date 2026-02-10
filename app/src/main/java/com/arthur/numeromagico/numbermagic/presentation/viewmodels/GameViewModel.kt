package com.arthur.numeromagico.numbermagic.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.arthur.numeromagico.features.numbermagic.presentation.screens.GameUiEvent
import com.arthur.numeromagico.features.numbermagic.presentation.screens.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs
import kotlin.random.Random

class GameViewModel : ViewModel() {

    private val magicNumber = Random.nextInt(1, 21)

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState

    fun onEvent(event: GameUiEvent) {
        when (event) {

            is GameUiEvent.OnInputChange -> {
                _uiState.value = _uiState.value.copy(
                    input = event.value
                )
            }

            GameUiEvent.OnTryClick -> {
                val userNumber = _uiState.value.input.toIntOrNull() ?: return
                val difference = abs(userNumber - magicNumber)

                val message = when {
                    difference == 0 -> "¡Correcto!"
                    difference <= 2 -> "Cerca"
                    difference <= 5 -> "Medio"
                    else -> "Lejos"
                }

                _uiState.value = _uiState.value.copy(
                    message = message,
                    input = ""
                )
            }
        }
    }
}
