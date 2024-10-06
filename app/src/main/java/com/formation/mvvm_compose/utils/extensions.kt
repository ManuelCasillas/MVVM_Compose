package com.formation.mvvm_compose.utils

import androidx.compose.ui.Modifier
import com.formation.domain.error.ErrorType
import com.formation.domain.error.getString

//This is for adding a new modifier when a value is true
fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

fun getStringFromError(error: Throwable): String =
    ((error as? ErrorType)?.getString() ?: error.message
    ?: error::class.java.name)