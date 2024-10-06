package com.formation.mvvm_compose.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.formation.mvvm_compose.ui.theme.MVVM_ComposeTheme

@Composable
fun BasicScreen (content: @Composable () -> Unit){
    MVVM_ComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            content()
        }
    }
}
