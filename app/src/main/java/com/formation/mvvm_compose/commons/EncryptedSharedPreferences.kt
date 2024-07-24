package com.formation.mvvm_compose.commons

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.formation.mvvm_compose.commons.Constants.PASSWORD_KEY
import com.formation.mvvm_compose.commons.Constants.USERNAME_KEY
import com.formation.mvvm_compose.models.Credential

private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    return EncryptedSharedPreferences.create(
        context,
        "secret_shared_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun saveCredentials(context: Context, credential: Credential) {
    val sharedPreferences = getEncryptedSharedPreferences(context)
    with(sharedPreferences.edit()) {
        putString(USERNAME_KEY, credential.user)
        putString(PASSWORD_KEY, credential.password)
        apply()
    }
}

fun getCredentials(context: Context): Credential? {
    val sharedPreferences = getEncryptedSharedPreferences(context)
    val username = sharedPreferences.getString(USERNAME_KEY, null)
    val password = sharedPreferences.getString(PASSWORD_KEY, null)

    return if (username != null && password != null) {
        Credential(username, password)
    } else {
        null
    }
}

fun removeCredentials(context: Context) {
    val sharedPreferences = getEncryptedSharedPreferences(context)
    with(sharedPreferences.edit()) {
        remove(USERNAME_KEY)
        remove(PASSWORD_KEY)
        apply()
    }
}


object Constants {
    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY = "password"
}