package com.formation.domain.usecase

import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetCharactersFavoritesListUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var getCharactersFavoritesListUseCase: GetCharactersFavoritesListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCharactersFavoritesListUseCase = GetCharactersFavoritesListUseCase(characterRepository)
    }

    @Test
    fun `invoke returns success with list of characters when repository call is successful`() = runTest {
        // Given
        val characters = listOf(
            Character(
                id = 1,
                title = "Iron Man",
                description = "Genius billionaire playboy philanthropist",
                thumbnail = "http://example.com/ironman.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = true
            ),
            Character(
                id = 2,
                title = "Captain America",
                description = "Super-Soldier",
                thumbnail = "http://example.com/captainamerica.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = true
            )
        )
        whenever(characterRepository.getFavoritesCharacters()).thenReturn(Result.success(characters))

        // When
        val result = getCharactersFavoritesListUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(characters, result.getOrNull())
    }

    @Test
    fun `invoke returns failure when repository call fails`() = runTest {
        // Given
        val exception = RuntimeException("Error fetching favorites")
        whenever(characterRepository.getFavoritesCharacters()).thenReturn(Result.failure(exception))

        // When
        val result = getCharactersFavoritesListUseCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}