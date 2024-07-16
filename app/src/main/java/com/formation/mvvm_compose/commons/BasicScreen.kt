package com.formation.mvvm_compose.commons

import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.formation.mvvm_compose.ui.theme.MVVM_ComposeTheme

@Composable
fun BasicScreen (content: @Composable () -> Unit){
    MVVM_ComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}