package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class SaveCharactersUseCase (
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characters: List<Character>) {
        return characterRepository.saveCharacters(characters)
    }
}