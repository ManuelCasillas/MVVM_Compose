package com.formation.mvvm_compose.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.formation.mvvm_compose.R
import com.formation.mvvm_compose.utils.removeCredentials

@Composable
fun Settings(logout: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LogoutConfirmation(logout)
    }
}


@Composable
fun LogoutConfirmation(logout: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = stringResource(id = R.string.settings_logout_confirmation_title))
            },
            text = {
                Text(stringResource(id = R.string.settings_logout_confirmation_msg))
            },
            confirmButton = {
                Button(
                    onClick = {
                        removeCredentials(context)
                        showDialog = false
                        logout()
                    }) {
                    Text(stringResource(id =R.string.settings_logout_button))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }) {
                    Text(stringResource(id =R.string.settings_logout_cancel_button))
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text(stringResource(id =R.string.settings_logout_button))
    }
}
