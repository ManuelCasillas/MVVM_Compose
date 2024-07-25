package com.formation.mvvm_compose.navigation.rootNavigation

import kotlinx.serialization.Serializable

sealed class Routes() {

    @Serializable
    object Login

    @Serializable
    object Home
}
