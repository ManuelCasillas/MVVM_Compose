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


fun List<Character>.toRoomCharacters(): List<CharacterRoom> =
        this.map { it.toRoomCharacter() }


fun List<CharacterRoom>.toCharactersList(): List<Character> =
        this.map { it.toCharacter() }


fun CharacterRoom.toCharacter(): Character =
        this.let {
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



