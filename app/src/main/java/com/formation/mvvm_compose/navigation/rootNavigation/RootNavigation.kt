package com.formation.mvvm_compose.navigation.rootNavigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.formation.mvvm_compose.screens.home.homeScaffold.MarvelApp
import com.formation.mvvm_compose.screens.login.LoginRoot

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login
    ){
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

        composable<Routes.Home> { backStackEntry ->
            MarvelApp(logout = {
                navController.navigate(Routes.Login) {
                    popUpTo(backStackEntry.destination.id) {
                        inclusive = true // para limpiar la pila de navegación
                    }
                }
            })
        }
    }
}