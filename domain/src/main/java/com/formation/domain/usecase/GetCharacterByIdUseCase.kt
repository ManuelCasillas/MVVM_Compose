package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class GetCharacterByIDUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characterId: Int): Result<Character> {
        return characterRepository.getCharacterById(characterId)
            .onSuccess { return Result.success(it) }
            .onFailure { return Result.failure(it) }
    }
}