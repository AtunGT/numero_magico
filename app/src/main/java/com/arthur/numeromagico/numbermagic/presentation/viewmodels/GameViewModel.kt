package com.arthur.numeromagico.numbermagic.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arthur.numeromagico.numbermagic.presentation.screens.GameEvent
import com.arthur.numeromagico.numbermagic.presentation.screens.GameState
import kotlin.math.abs

class GameViewModel : ViewModel() {


    var state by mutableStateOf(GameState(targetNumber = generateRandomNumber()))
        private set


    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.OnGuessChanged -> {
                if (event.value.all { it.isDigit() }) {
                    state = state.copy(userGuess = event.value)
                }
            }
            GameEvent.OnSubmitGuess -> processGuess()
            GameEvent.OnRestartGame -> resetGame()
        }
    }

    private fun processGuess() {
        val guess = state.userGuess.toIntOrNull() ?: return
        val target = state.targetNumber
        val diff = abs(target - guess)
        val newAttempts = state.attempts + 1

        if (diff == 0) {
            state = state.copy(
                isGameOver = true,
                feedback = "¡Ganaste! El número era $target",
                attempts = newAttempts
            )
        } else {
            val hint = when {
                diff <= 5 -> "¡Muy Cerca!"
                diff <= 15 -> "Medio (Ni frío ni caliente)"
                else -> "Lejos..."
            }

            state = state.copy(
                feedback = hint,
                attempts = newAttempts,
                userGuess = ""
            )
        }
    }

    private fun resetGame() {
        state = GameState(targetNumber = generateRandomNumber())
    }

    private fun generateRandomNumber(): Int = (1..100).random()
}
