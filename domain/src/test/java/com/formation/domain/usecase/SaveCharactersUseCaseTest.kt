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
class SaveCharactersUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var saveCharactersUseCase: SaveCharactersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        saveCharactersUseCase = SaveCharactersUseCase(characterRepository)
    }

    @Test
    fun `invoke calls repository to save characters`() = runTest {
        // Given
        val characters = listOf(
            Character(
                id = 1,
                title = "Spider-Man",
                description = "Friendly neighborhood hero",
                thumbnail = "http://example.com/spiderman.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = false
            ),
            Character(
                id = 2,
                title = "Iron Man",
                description = "Genius billionaire",
                thumbnail = "http://example.com/ironman.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = true
            )
        )

        whenever(characterRepository.saveCharacters(characters)).thenReturn(Unit)

        // When
        saveCharactersUseCase(characters)

        // Then
        verify(characterRepository).saveCharacters(characters)
    }

    @Test
    fun `invoke calls repository with empty list`() = runTest {
        // Given
        val emptyList = emptyList<Character>()

        whenever(characterRepository.saveCharacters(emptyList)).thenReturn(Unit)

        // When
        saveCharactersUseCase(emptyList)

        // Then
        verify(characterRepository).saveCharacters(emptyList)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke propagates exception when repository save fails`() = runTest {
        // Given
        val characters = listOf(
            Character(
                id = 3,
                title = "Captain America",
                description = "Super soldier",
                thumbnail = "http://example.com/captainamerica.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = false
            )
        )

        whenever(characterRepository.saveCharacters(characters)).thenThrow(RuntimeException("Save failed"))

        // When
        saveCharactersUseCase(characters)

        // Then
        // The test will pass if a RuntimeException is thrown
    }
}