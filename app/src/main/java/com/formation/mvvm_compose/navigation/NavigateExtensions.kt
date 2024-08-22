package com.formation.mvvm_compose.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigatePoppingUpToStartDestination(route: String) {
    navigate(route) {
        //This made that when you navigate between screen in bottom choice, it remains the same screen when you came back.
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
