package com.formation.data.database

import com.formation.domain.model.Character

fun Character.toRoomCharacter() =
        CharacterRoom(
                id = id,
                title = title,
                description = description,
                thumbnail = thumbnail,
//                references = references,
//                urls = urls,
                favorite = favorite
        )


fun List<Character>.toRoomCharacters(): List<CharacterRoom> {
        return this.map {
                CharacterRoom(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        thumbnail = it.thumbnail,
//                        references = it.references,
//                        urls = it.urls,
                        favorite = it.favorite
                )
        }
}



fun List<CharacterRoom>.toCharactersList(): List<Character> {
        return this.map {
                Character(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        thumbnail = it.thumbnail,
                        references = null,
                        urls = ArrayList(),
                        favorite = it.favorite
                )
        }
}

