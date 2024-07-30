package com.formation.mvvm_compose.navigation.mainNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.formation.mvvm_compose.screens.home.characters.CharactersRoot
import com.formation.mvvm_compose.settings.Settings

@Composable
fun Navigation(navController: NavHostController, logout: () -> Unit) {


    NavHost(
        navController = navController,
        startDestination = Feature.CHARACTERS.route,
    ) {
        charactersNav(navController)
        comicsNav(navController)
        eventsNav(navController)
        composable(NavCommand.ContentType(Feature.SETTINGS)) {
            Settings(logout)
        }
    }
}


private fun NavGraphBuilder.charactersNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersRoot()
        }

        composable(NavCommand.ContentTypeDetail(Feature.CHARACTERS)) {
            DefaultScreen("detalle caracteres")
        }
    }
}


private fun NavGraphBuilder.comicsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            DefaultScreen("comics")

        }

        composable(NavCommand.ContentTypeDetail(Feature.COMICS)) {
            DefaultScreen("detalle comics")
        }
    }
}

private fun NavGraphBuilder.eventsNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            DefaultScreen("eventos")

        }

        composable(NavCommand.ContentTypeDetail(Feature.EVENTS)) {
            DefaultScreen("detalle eventos")
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}




@Composable
fun DefaultScreen(screenName: String){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = screenName)
    }
}
