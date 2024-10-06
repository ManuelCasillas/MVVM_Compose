package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ToggleCharacterFavoriteUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var toggleCharacterFavoriteUseCase: ToggleCharacterFavoriteUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        toggleCharacterFavoriteUseCase = ToggleCharacterFavoriteUseCase(characterRepository)
    }

    @Test
    fun `invoke calls repository to update character favorite status`() = runTest {
        // Given
        val character = Character(
            id = 1,
            title = "Spider-Man",
            description = "Friendly neighborhood hero",
            thumbnail = "http://example.com/spiderman.jpg",
            references = emptyList(),
            urls = emptyList(),
            favorite = false
        )

        whenever(characterRepository.updateCharacterFavorite(character)).thenReturn(Unit)

        // When
        toggleCharacterFavoriteUseCase(character)

        // Then
        verify(characterRepository).updateCharacterFavorite(character)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke propagates exception when repository update fails`() = runTest {
        // Given
        val character = Character(
            id = 2,
            title = "Iron Man",
            description = "Genius billionaire",
            thumbnail = "http://example.com/ironman.jpg",
            references = emptyList(),
            urls = emptyList(),
            favorite = true
        )

        whenever(characterRepository.updateCharacterFavorite(character)).thenThrow(RuntimeException("Update failed"))

        // When
        toggleCharacterFavoriteUseCase(character)

        // Then
        // The test will pass if a RuntimeException is thrown
    }
}