package com.formation.mvvm_compose.login


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.formation.mvvm_compose.commons.BasicScreen

@Composable
fun LoginRoot(vm: LoginViewModel = viewModel(), onLogin:() -> Unit) {
    BasicScreen(content = {
        Login(vm.state, onLogin, vm::onLoginClick, vm::userValueChanged, vm::passValueChanged)
    })
}

@Composable
fun Login(state: LoginViewModel.UiState,
          onLogin:()-> Unit,
          onLoginClick: (user: String, pass: String) -> Unit,
          userValueChanged: () -> Unit,
          passValueChanged: () -> Unit
) {

    LaunchedEffect(state.loggedIn) {
        if (state.loggedIn) onLogin()
    }

    val message = when {
        state.message != null -> state.message
        else -> ""
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.imePadding()) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(IntrinsicSize.Min)

        ) {
            val focusManager = LocalFocusManager.current

            var user by rememberSaveable { mutableStateOf(value = "") }
            var pass by rememberSaveable { mutableStateOf(value = "") }
            val buttonEnabled = pass.isNotEmpty() && user.isNotEmpty()


            StateUserTextField(
                value = user,
                onValueChange = {
                    user = it
                    userValueChanged()
                },
                isError = state.isUserError
            )
            StatePasswordTextField(
                value = pass,
                onValueChange = {
                    pass = it
                    passValueChanged()
                },
                isError = state.isPassError
            )

            Button(
                enabled = buttonEnabled,
                onClick = {
                    focusManager.clearFocus()
                    onLoginClick(user, pass)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),

                ) {
                Text(text = "Login")
            }

            Text(text = message)
        }
    }
}

@Composable
private fun StateUserTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    TextField(
        value = value,
        label = { Text(text = "Usuario") },
        placeholder = { Text(text = "Manuel@") },
        onValueChange = onValueChange,
        isError = isError,
        singleLine = true,
        textStyle = TextStyle(
            color = if (isError) Color.Red else Color.Black
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email)
    )
}

@Composable
private fun StatePasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = value,
        placeholder = { Text(text = "Contraseña") },
        onValueChange = onValueChange,
        isError = isError,
        singleLine = true,
        textStyle = TextStyle(
            color = if (isError) Color.Red else Color.Black
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passwordVisible,
                onCheckedChange = { passwordVisible = it },
            ) {
                Crossfade(targetState = passwordVisible) { visible ->
                    if (visible) {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            tint = if (isError) Color.Red else Color.Black,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            tint = if (isError) Color.Red else Color.Black,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
    )
}
