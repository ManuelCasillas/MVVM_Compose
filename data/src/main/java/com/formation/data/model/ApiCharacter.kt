package com.formation.data.model

import com.antonioleiva.marvelcompose.data.network.entities.ApiReferenceList
import com.antonioleiva.marvelcompose.data.network.entities.ApiUrl
import com.formation.domain.model.Character
import com.formation.domain.model.Url

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ApiThumbnail,
    val comics: ApiReferenceList,
    val events: ApiReferenceList,
    val series: ApiReferenceList,
    val stories: ApiReferenceList,
    val urls: List<ApiUrl>,
    val modified: String,
    val resourceURI: String
)


fun ApiCharacter.toListCharacter(): Character {
    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        urls = urls.map { Url(it.type, it.url) },
        favorite = false
    )
}