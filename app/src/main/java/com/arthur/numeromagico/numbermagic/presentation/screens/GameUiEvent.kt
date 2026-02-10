package com.arthur.numeromagico.features.numbermagic.presentation.screens



sealed class GameUiEvent {
    data class OnInputChange(val value: String) : GameUiEvent()
    object OnTryClick : GameUiEvent()
}
