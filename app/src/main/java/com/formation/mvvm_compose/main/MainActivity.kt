package com.formation.mvvm_compose.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.formation.mvvm_compose.MarvelApp
import com.formation.mvvm_compose.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : ComponentActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }

        setContent {
            MarvelApp()
//            Navigation()
        }
    }
}