package com.formation.domain.usecase.mocks

import com.formation.domain.model.Character

val mockCharacter = Character(
    id = 1,
    title = "Mock Title",
    description = "Mock Description",
    thumbnail = "http://mock.thumbnail.url",
    references = null,
    urls = emptyList(),
    favorite = true
)
