package com.formation.mvvm_compose.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class LoginViewModel: ViewModel() {

    var state by mutableStateOf(UiState())    //no necesitamos al remember porque no tiene composable y el viewmodel funciona como remember
        private set

    fun onLoginClick(user: String, pass: String) {
        val isUserValid = user.contains("@")
        val isPassValid = pass.length > 5

        state = UiState(
            loggedIn = isUserValid && isPassValid,
            isUserError = when {
                !isUserValid -> true
                else -> false
            },
            isPassError = when {
                !isPassValid -> true
                else -> false
            }
        )
    }

    fun userValueChanged() {
        state = state.copy(isUserError = false)
    }
    fun passValueChanged() {
        state = state.copy(isPassError = false)
    }

    data class UiState(
        val loggedIn: Boolean = false,
        val isUserError: Boolean = false,
        val isPassError: Boolean = false
    )
}