package com.arthur.numeromagico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arthur.numeromagico.numbermagic.presentation.screens.GameScreen
import com.arthur.numeromagico.numbermagic.presentation.viewmodels.GameViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: GameViewModel = viewModel()
            val state by viewModel.uiState.collectAsState()

            GameScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}
