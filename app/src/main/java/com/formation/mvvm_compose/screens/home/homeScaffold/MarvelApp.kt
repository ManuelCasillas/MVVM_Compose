package com.formation.mvvm_compose.screens.home.homeScaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.formation.mvvm_compose.R
import com.formation.mvvm_compose.utils.BasicScreen
import com.formation.mvvm_compose.navigation.mainNavigation.AppBarIcon
import com.formation.mvvm_compose.navigation.mainNavigation.AppBottomNavigation
import com.formation.mvvm_compose.navigation.mainNavigation.DrawerContent
import com.formation.mvvm_compose.navigation.mainNavigation.MarvelTopAppBar
import com.formation.mvvm_compose.navigation.mainNavigation.Navigation
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MarvelApp(appState: MarvelAppState = rememberMarvelAppState(), logout: () -> Unit) {

    BasicScreen {
        ModalNavigationDrawer(
            drawerState = appState.drawerState,
            drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS,
                    selectedIndex = appState.drawerSelectedIndex,
                    onOptionClick = { appState.onDrawerOptionClick(it) }
                )
            }
        }) {
            Scaffold(
                topBar = {
                    MarvelTopAppBar(
                        title = { Text(stringResource(id = R.string.app_name)) },
                        navigationIcon = {
                            if (appState.showUpNavigation) {
                                AppBarIcon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    onClick = { appState.onUpClick() })
                            } else {
                                AppBarIcon(
                                    imageVector = Icons.Default.Menu,
                                    onClick = { appState.onMenuClick() }
                                )
                            }
                        }
                    )
                },
                bottomBar = {
                    if (appState.showBottomNavigation) { // When you customize this one hiding and showing, add an undesired blink at bottom bar
                        AppBottomNavigation(
                            bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS,
                            currentRoute = appState.currentRoute,
                            onNavItemClick = { appState.onNavItemClick(it) })
                    }
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(appState.navController, logout)
                }
            }
        }

        SetStatusBarColorEffect()
    }
}

@Composable
private fun SetStatusBarColorEffect(
    color: Color = MaterialTheme.colorScheme.secondary,
    systemUiController: SystemUiController = rememberSystemUiController()
) {

    SideEffect {
        systemUiController.setStatusBarColor(color)
    }
}
