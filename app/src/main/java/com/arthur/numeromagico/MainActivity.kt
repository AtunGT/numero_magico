package com.arthur.numeromagico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arthur.numeromagico.numbermagic.presentation.screens.GameRoute

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameRoute()
        }
    }
}
