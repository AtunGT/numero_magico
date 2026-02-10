package com.arthur.numeromagico.numbermagic.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arthur.numeromagico.numbermagic.presentation.viewmodels.GameViewModel

@Composable
fun GameRoute() {

    val viewModel: GameViewModel = viewModel()
    val state by viewModel.uiState.collectAsState()

    GameScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}
