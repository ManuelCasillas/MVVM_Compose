package com.formation.data

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.formation.data.database.CharacterDao
import com.formation.data.database.CharacterDatabase
import com.formation.data.mocks.mockCharacterRoom
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {

    private lateinit var database: RoomDatabase
    private lateinit var characterDao: CharacterDao

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            CharacterDatabase::class.java
        ).build()

        // Get the DAO
        characterDao = (database as CharacterDatabase).characterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertCharacters_shouldInsertCharactersIntoDatabase() = runBlocking {
        // Act
        characterDao.insertCharacters(listOf(mockCharacterRoom))

        // Assert
        val characters = characterDao.getAll()
        assertEquals(1, characters.size)
        assertEquals(mockCharacterRoom, characters[0])
    }

    @Test
    fun findByIdShouldReturnCharacterWithSpecifiedId() = runBlocking {

        characterDao.insertCharacters(listOf(mockCharacterRoom))

        // Act
        val character = characterDao.findById(1)

        // Assert
        assertEquals(mockCharacterRoom, character)
    }

    @Test
    fun getFavoritesShouldReturnFavoriteCharacters() = runBlocking {
        // Arrange
        val favoriteCharacter = mockCharacterRoom.copy(
            id = 1,
            favorite = true
        )
        val nonFavoriteCharacter =mockCharacterRoom.copy(
            id = 2,
            favorite = false
        )
        characterDao.insertCharacters(listOf(favoriteCharacter, nonFavoriteCharacter))

        // Act
        val favorites = characterDao.getFavorites()

        // Assert
        assertEquals(1, favorites.size)
        assertEquals(favoriteCharacter, favorites[0])
    }

    @Test
    fun characterCountShouldReturnNumberOfCharactersInDatabase() = runBlocking {
        // Arrange
        characterDao.insertCharacters(
            listOf(
                mockCharacterRoom.copy(id = 1, favorite = false),
                mockCharacterRoom.copy(id = 2, favorite = true)
            )
        )

        // Act
        val count = characterDao.characterCount()

        // Assert
        assertEquals(2, count)
    }

    @Test
    fun updateCharacterShouldUpdateCharacterInDatabase() = runBlocking {
        // Arrange
        val newTitle = "New Title"
        characterDao.insertCharacters(listOf(mockCharacterRoom))

        // Act
        val updatedCharacterRoom = mockCharacterRoom.copy(title = newTitle)
        characterDao.updateCharacter(updatedCharacterRoom)

        // Assert
        val character = characterDao.findById(1)
        assertEquals(newTitle, character.title)
    }
}