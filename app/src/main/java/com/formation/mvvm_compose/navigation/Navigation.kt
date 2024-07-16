package com.formation.mvvm_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.formation.mvvm_compose.home.Home
import com.formation.mvvm_compose.login.LoginRoot

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ) {
        composable<Routes.Login> { backStackEntry ->
            LoginRoot(
                onLogin = {
                    navController.navigate(Routes.Home) {
                        popUpTo(backStackEntry.destination.id) {
                            inclusive = true // para limpiar la pila de navegación
                        }
                    }
                })
        }
        composable<Routes.Home> {
            Home()
        }
    }
}

