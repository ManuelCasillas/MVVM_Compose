package com.formation.data.repository

import com.formation.data.api.CharactersApi
import com.formation.data.dataSource.makeRequest
import com.formation.data.model.toListCharacter
import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: CharactersApi
): CharacterRepository {

    override suspend fun getCharacterList(): Result<List<Character>> {
        return makeRequest(
            apiRequest = { api.getCharacters(0, 10) },
            transform = { apiResponse -> apiResponse?.data?.results?.map { it.toListCharacter() } }
        )
    }
}
