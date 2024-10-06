package com.formation.domain.usecase

import com.formation.domain.repository.CharacterRepository
import com.formation.domain.usecase.mocks.mockCharacter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetCharacterByIDUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var getCharacterByIDUseCase: GetCharacterByIDUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getCharacterByIDUseCase = GetCharacterByIDUseCase(characterRepository)
    }

    @Test
    fun `invoke should return character when repository returns success`() = runBlocking {
        // Given
        val characterId = 1
        val expectedCharacter = mockCharacter
        `when`(characterRepository.getCharacterById(characterId)).thenReturn(Result.success(expectedCharacter))

        // When
        val result = getCharacterByIDUseCase.invoke(characterId)

        // Then
        assert(result.isSuccess)
        assertEquals(expectedCharacter, result.getOrNull())
    }

    @Test
    fun `invoke should return failure when repository returns failure`() = runBlocking {
        // Given
        val characterId = 1
        val expectedException = Exception("Character not found")
        `when`(characterRepository.getCharacterById(characterId)).thenReturn(Result.failure(expectedException))

        // When
        val result = getCharacterByIDUseCase.invoke(characterId)

        // Then
        assert(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
    }
}
