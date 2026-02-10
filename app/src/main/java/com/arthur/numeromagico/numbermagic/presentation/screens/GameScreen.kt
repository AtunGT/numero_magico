package com.arthur.numeromagico.numbermagic.presentation.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arthur.numeromagico.numbermagic.presentation.viewmodels.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val state = viewModel.state


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Magic Number",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = state.feedback,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = if (state.isGameOver) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (!state.isGameOver) {
            GameInputSection(
                guess = state.userGuess,
                onGuessChanged = { viewModel.onEvent(GameEvent.OnGuessChanged(it)) },
                onSubmit = { viewModel.onEvent(GameEvent.OnSubmitGuess) }
            )
        } else {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Intentos totales: ${state.attempts}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.onEvent(GameEvent.OnRestartGame) }
                ) {
                    Text("Jugar de Nuevo")
                }
            }
        }
    }
}

@Composable
fun GameInputSection(
    guess: String,
    onGuessChanged: (String) -> Unit,
    onSubmit: () -> Unit
) {
    OutlinedTextField(
        value = guess,
        onValueChange = onGuessChanged,
        label = { Text("Ingresa un número") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.fillMaxWidth(0.7f)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onSubmit,
        enabled = guess.isNotEmpty(),
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Text("Probar Suerte")
    }
}