package com.renansilva235.controlador_habitos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.renansilva235.controlador_habitos.presentation.screens.user.RegisterScreen
import com.renansilva235.controlador_habitos.ui.theme.Controlador_HabitosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Controlador_HabitosTheme {
                RegisterScreen()
            }
        }
    }
}

