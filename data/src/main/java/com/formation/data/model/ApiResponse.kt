package com.formation.data.model

import com.antonioleiva.marvelcompose.data.network.entities.ApiData

data class ApiResponse<T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: ApiData<T>,
    val etag: String,
    val status: String
)