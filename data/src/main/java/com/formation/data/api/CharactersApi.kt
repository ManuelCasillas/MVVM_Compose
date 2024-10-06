package com.formation.data.api

import com.formation.data.model.ApiResponse
import com.formation.data.model.ApiCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ApiResponse<ApiCharacter>>

}