package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class ToggleCharacterFavoriteUseCase(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(character: Character) {
        return characterRepository.updateCharacterFavorite(character)
    }
}