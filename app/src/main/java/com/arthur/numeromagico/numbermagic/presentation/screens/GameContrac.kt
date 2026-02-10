package com.arthur.numeromagico.numbermagic.presentation.screens

data class GameState(
    val targetNumber: Int,
    val userGuess: String = "",
    val feedback: String = "¡Adivina el número (1-100)!",
    val isGameOver: Boolean = false,
    val attempts: Int = 0
)


sealed interface GameEvent {
    data class OnGuessChanged(val value: String) : GameEvent
    data object OnSubmitGuess : GameEvent
    data object OnRestartGame : GameEvent
}