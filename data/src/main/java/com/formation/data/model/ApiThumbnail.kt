package com.formation.data.model

data class ApiThumbnail(
    val extension: String,
    val path: String
)

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")