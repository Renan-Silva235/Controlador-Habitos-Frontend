package com.renansilva235.controlador_habitos.presentation.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun FeedbackSnackbar(message: String?, snackbarHostState: SnackbarHostState, onDismiss: () -> Unit = {}){
    LaunchedEffect(message) {
        if(!message.isNullOrBlank()){
            snackbarHostState.showSnackbar(message)
            onDismiss
        }
    }
}