package com.formation.data.repository

import com.formation.data.api.CharactersApi
import com.formation.data.dataSource.makeRequest
import com.formation.data.database.CharacterDao
import com.formation.data.database.toCharacter
import com.formation.data.database.toCharactersList
import com.formation.data.database.toRoomCharacter
import com.formation.data.database.toRoomCharacters
import com.formation.data.model.toListCharacter
import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val api: CharactersApi,
    private val characterDao: CharacterDao
): CharacterRepository {

    override suspend fun getCharacterList(): Result<List<Character>> {
        return makeRequest(
            apiRequest = { api.getCharacters(0, 10) },
            transform = { apiResponse ->
                apiResponse?.data?.results?.map { it.toListCharacter() }
            }
        )
    }

    override suspend fun saveCharacters(characters: List<Character>) {
        withContext(Dispatchers.IO) {
            characterDao.insertCharacters(characters.toRoomCharacters())
        }
    }

    override suspend fun updateCharacterFavorite(character: Character) {
        withContext(Dispatchers.IO) {
            characterDao.updateCharacter(character.toRoomCharacter())
        }
    }

    override suspend fun getFavoritesCharacters(): Result<List<Character>> =
        withContext(Dispatchers.IO) {
            Result.success(characterDao.getFavorites().toCharactersList())
        }

    override suspend fun getCharacterById(characterId: Int): Result<Character> =
        withContext(Dispatchers.IO) {
            Result.success(characterDao.findById(characterId).toCharacter())
        }
}
