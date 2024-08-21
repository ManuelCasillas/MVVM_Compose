package com.formation.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterRoom::class], version = 1)
abstract class CharacterDatabase: RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}
