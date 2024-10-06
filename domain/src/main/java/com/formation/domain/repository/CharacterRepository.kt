package com.formation.domain.repository

import com.formation.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacterList(): Result<List<Character>>
    suspend fun saveCharacters(characters: List<Character>)
    suspend fun updateCharacterFavorite(character: Character)
    suspend fun getFavoritesCharacters(): Result<List<Character>>
    suspend fun getCharacterById(characterId: Int): Result<Character>
}