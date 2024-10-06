package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository

class GetCharactersListUseCase(
   private val characterRepository: CharacterRepository
) {
      suspend operator fun invoke(): Result<List<Character>> {
          //We always call the service to refresh data.
          //Only use room for favorite characters
         return characterRepository.getCharacterList()
            .onSuccess { return Result.success(it) }
            .onFailure { return Result.failure(it) }
      }

}