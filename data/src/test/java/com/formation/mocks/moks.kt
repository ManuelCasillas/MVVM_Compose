package com.formation.mocks

import com.antonioleiva.marvelcompose.data.network.entities.ApiData
import com.antonioleiva.marvelcompose.data.network.entities.ApiReferenceList
import com.antonioleiva.marvelcompose.data.network.entities.ApiUrl
import com.formation.data.database.CharacterRoom
import com.formation.data.model.ApiCharacter
import com.formation.data.model.ApiResponse
import com.formation.data.model.ApiThumbnail
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

val mockCharacterRoom = CharacterRoom(
    id = 1,  // You can use any valid integer value
    title = "Mock Title",  // Provide a non-empty string
    description = "Mock Description",  // Provide a non-empty string
    thumbnail = "http://mock.thumbnail.url",  // Provide a mock URL
    favorite = false  // Set to either true or false
)


// Create an instance of ApiThumbnail
val mockApiThumbnail = ApiThumbnail(
    path = "http://mock.thumbnail.path",
    extension = "jpg"
)

// Create an instance of ApiReferenceList for comics
val mockApiComics = ApiReferenceList(
    available = 10,
    collectionURI = "http://mock.collection.uri/comics",
    items = emptyList(),
    returned = 10
)

// Create instances of ApiReferenceList for events, series, and stories
val mockApiEvents = ApiReferenceList(
    available = 5,
    collectionURI = "http://mock.collection.uri/events",
    items = emptyList(),
    returned = 5
)

val mockApiSeries = ApiReferenceList(
    available = 7,
    collectionURI = "http://mock.collection.uri/series",
    items = emptyList(),
    returned = 7
)

val mockApiStories = ApiReferenceList(
    available = 12,
    collectionURI = "http://mock.collection.uri/stories",
    items = emptyList(),
    returned = 12
)

// Create an instance of ApiUrl
val mockApiUrl = ApiUrl(
    type = "detail",
    url = "http://mock.url/detail"
)

// Create an instance of ApiCharacter
val mockApiCharacter = ApiCharacter(
    id = 1,
    name = "Mock Character Name",
    description = "This is a mock description of the character.",
    thumbnail = mockApiThumbnail,
    comics = mockApiComics,
    events = mockApiEvents,
    series = mockApiSeries,
    stories = mockApiStories,
    urls = listOf(mockApiUrl),
    modified = "2023-08-23T00:00:00Z",
    resourceURI = "http://mock.resource.uri"
)



val mockApiData = ApiData(
    count = 1,
    limit = 10,
    offset = 0,
    results = listOf(mockApiCharacter),
    total = 1
)

val mockApiResponse = ApiResponse(
    attributionHTML = "Mock Attribution HTML",
    attributionText = "Mock Attribution Text",
    code = 200,
    copyright = "Mock Copyright",
    data = mockApiData,
    etag = "Mock ETag",
    status = "Mock Status"
)