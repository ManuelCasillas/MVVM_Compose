package com.formation.mvvm_compose.navigation.mainNavigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    inline fun <reified T> serializableType(): NavType<T> =
        object : NavType<T>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): T? =
            bundle.getString(key)?.let { Json.decodeFromString(it)}

        override fun parseValue(value: String): T =
            Json.decodeFromString(Uri.decode(value))

        override fun serializeAsValue(value: T): String =
            Uri.encode(Json.encodeToString(value))

        override fun put(bundle: Bundle, key: String, value: T) =
            bundle.putString(key, Json.encodeToString(value))
    }

    inline fun <reified T : Enum<T>> serializableEnum(): NavType<T> = object : NavType<T>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): T? =
            bundle.getString(key)?.let { enumValueOf<T>(it) }

        override fun parseValue(value: String): T =
            enumValueOf(value)

        override fun serializeAsValue(value: T): String =
            value.name

        override fun put(bundle: Bundle, key: String, value: T) =
            bundle.putString(key, value.name)
    }

}