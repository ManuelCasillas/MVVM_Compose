package com.formation.mvvm_compose.navigation

import kotlinx.serialization.Serializable

sealed class Routes() {
    @Serializable
    object Login
    @Serializable
    object Home
}
