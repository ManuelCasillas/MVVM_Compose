package com.formation.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterRoom (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
//    val references: List<ReferenceList>? = null,
//    val urls: List<Url>,
    val favorite: Boolean
)