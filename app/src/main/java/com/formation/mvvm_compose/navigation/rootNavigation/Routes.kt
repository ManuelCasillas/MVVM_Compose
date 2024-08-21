package com.formation.mvvm_compose.navigation.rootNavigation

import kotlinx.serialization.Serializable

sealed class Routes() {

    @Serializable
    data object Login

    @Serializable
    data object Home
}
