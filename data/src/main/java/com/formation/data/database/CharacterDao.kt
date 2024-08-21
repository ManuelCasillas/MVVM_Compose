package com.formation.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterRoom")
    fun getAll(): List<CharacterRoom>

    @Query("SELECT * FROM CharacterRoom WHERE id = :id")
    fun findById(id: Int): CharacterRoom

    @Query("SELECT * FROM CharacterRoom WHERE favorite = 1")
    fun getFavorites(): List<CharacterRoom>

    @Query("SELECT COUNT(id) FROM CharacterRoom")
    fun characterCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(movies: List<CharacterRoom>)

    @Update
    fun updateCharacter(character: CharacterRoom)
}
