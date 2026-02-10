package com.arthur.numeromagico.numbermagic.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arthur.numeromagico.features.numbermagic.presentation.screens.GameUiEvent
import com.arthur.numeromagico.features.numbermagic.presentation.screens.GameUiState

@Composable
fun GameScreen(
    state: GameUiState,
    onEvent: (GameUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Adivina el número mágico", fontSize = 22.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.input,
            onValueChange = {
                onEvent(GameUiEvent.OnInputChange(it))
            },
            label = { Text("Ingresa un número") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onEvent(GameUiEvent.OnTryClick)
            }
        ) {
            Text("Probar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(state.message, fontSize = 18.sp)
    }
}
