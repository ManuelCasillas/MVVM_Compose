package com.formation.domain.repository

import com.formation.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacterList(): Result<List<Character>>
}