package com.formation.domain.usecase
import com.formation.domain.model.Character
import com.formation.domain.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetCharactersListUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var getCharactersListUseCase: GetCharactersListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCharactersListUseCase = GetCharactersListUseCase(characterRepository)
    }

    @Test
    fun `invoke returns success with list of characters when repository call is successful`() = runTest {
        // Given
        val characters = listOf(
            Character(
                id = 1,
                title = "Iron Man",
                description = "Genius billionaire",
                thumbnail = "http://example.com/ironman.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = false
            ),
            Character(
                id = 2,
                title = "Captain America",
                description = "Super soldier",
                thumbnail = "http://example.com/cap.jpg",
                references = emptyList(),
                urls = emptyList(),
                favorite = true
            )
        )
        whenever(characterRepository.getCharacterList()).thenReturn(Result.success(characters))

        // When
        val result = getCharactersListUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(characters, result.getOrNull())
        result.getOrNull()?.let { resultList ->
            assertEquals(2, resultList.size)
            assertEquals("Iron Man", resultList[0].title)
            assertEquals("Captain America", resultList[1].title)
        }
    }

    @Test
    fun `invoke returns success with empty list when repository returns empty list`() = runTest {
        // Given
        val emptyList = emptyList<Character>()
        whenever(characterRepository.getCharacterList()).thenReturn(Result.success(emptyList))

        // When
        val result = getCharactersListUseCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(emptyList, result.getOrNull())
        result.getOrNull()?.let { resultList ->
            assertTrue(resultList.isEmpty())
        }
    }

    @Test
    fun `invoke returns failure when repository call fails`() = runTest {
        // Given
        val exception = RuntimeException("Error fetching characters")
        whenever(characterRepository.getCharacterList()).thenReturn(Result.failure(exception))

        // When
        val result = getCharactersListUseCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}