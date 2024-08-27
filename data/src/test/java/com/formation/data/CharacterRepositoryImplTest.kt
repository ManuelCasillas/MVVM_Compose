package com.formation.data

import com.formation.data.api.CharactersApi
import com.formation.data.database.CharacterDao
import com.formation.data.database.toCharacter
import com.formation.data.database.toCharactersList
import com.formation.data.database.toRoomCharacter
import com.formation.data.database.toRoomCharacters
import com.formation.data.model.ApiCharacter
import com.formation.data.model.ApiResponse
import com.formation.data.model.toListCharacter
import com.formation.data.repository.CharacterRepositoryImpl
import com.formation.domain.model.Character
import com.formation.mocks.mockApiCharacter
import com.formation.mocks.mockApiData
import com.formation.mocks.mockApiResponse
import com.formation.mocks.mockCharacter
import com.formation.mocks.mockCharacterRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


@ExperimentalCoroutinesApi
class CharacterRepositoryImplTest {

    @Mock
    private lateinit var api: CharactersApi

    @Mock
    private lateinit var characterDao: CharacterDao

    private lateinit var characterRepository: CharacterRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        characterRepository = CharacterRepositoryImpl(api, characterDao)
    }

    @Test
    fun `getCharacterList should return list of characters on success`() = runTest {
        // Arrange
        val apiResponse = mockApiResponse
        val characterList = listOf(mockApiCharacter.toListCharacter())

        // Mocking the API call to return the mocked ApiResponse
        `when`(api.getCharacters(0, 10)).thenReturn(Response.success(apiResponse))

        // Act
        val result = characterRepository.getCharacterList()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(characterList, result.getOrNull())
    }

    @Test
    fun `saveCharacters should call insertCharacters on characterDao`() = runTest {
        // Arrange

        val characters = listOf(mockCharacter)
        val roomCharacters = characters.toRoomCharacters()

        // Act
        characterRepository.saveCharacters(characters)

        // Assert
        verify(characterDao).insertCharacters(roomCharacters)
    }

    @Test
    fun `updateCharacterFavorite should call updateCharacter on characterDao`() = runTest {
        // Arrange
        val roomCharacter = mockCharacter.toRoomCharacter()

        // Act
        characterRepository.updateCharacterFavorite(mockCharacter)

        // Assert
        verify(characterDao).updateCharacter(roomCharacter)
    }

    @Test
    fun `getFavoritesCharacters should return list of favorite characters`() = runTest {
        // Arrange
        val favoriteCharacters = listOf(mockCharacterRoom)

        `when`(characterDao.getFavorites()).thenReturn(favoriteCharacters)

        // Act
        val result = characterRepository.getFavoritesCharacters()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(favoriteCharacters.toCharactersList(), result.getOrNull())
    }

    @Test
    fun `getCharacterById should return character by id`() = runTest {
        // Arrange
        val characterId = 1
        `when`(characterDao.findById(characterId)).thenReturn(mockCharacterRoom)

        // Act
        val result = characterRepository.getCharacterById(characterId)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(mockCharacterRoom.toCharacter(), result.getOrNull())
    }
}




