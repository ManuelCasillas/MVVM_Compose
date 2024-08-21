package com.formation.domain.model

data class Character(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val references: List<ReferenceList>? = null,
    val urls: List<Url>,
    var favorite: Boolean = false
)
