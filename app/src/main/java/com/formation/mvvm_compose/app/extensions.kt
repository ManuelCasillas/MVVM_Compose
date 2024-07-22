package com.formation.mvvm_compose.app

import androidx.compose.ui.Modifier

//This is for adding a new modifier when a value is true
fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}