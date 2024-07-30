package com.antonioleiva.marvelcompose.data.network.entities

import com.formation.data.model.ApiReference

data class ApiReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiReference>?,
    val returned: Int
)