package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class GetCharactersFavoritesListUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<Character>> {
        return characterRepository.getFavoritesCharacters()
            .onSuccess { return Result.success(it) }
            .onFailure { return Result.failure(it) }
    }
}