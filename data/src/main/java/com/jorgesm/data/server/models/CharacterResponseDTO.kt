package com.jorgesm.data.server.models

import java.io.Serializable

data class CharacterResponseDTO(
    val code: Long? = null,
    val status: String? = null,
    val copyright: String? = null,
    val attributionText: String? = null,
    val attributionHTML: String? = null,
    val eTag: String? = null,
    val data: Data? = null
) : Serializable

data class Data(
    val offset: Long? = null,
    val limit: Long? = null,
    val total: Long? = null,
    val count: Long? = null,
    val results: List<Result>? = null
)

data class Result(
    val id: Long ,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null, // FIXME: ver en doc si viene como long o Date
    val thumbnail: Thumbnail? = null,
    val resourceURI: String? = null,
    val comics: Comics? = null,
    val series: Comics? = null,
    val stories: Stories? = null,
    val events: Comics? = null,
    val urls: List<URL>? = null
)

data class Comics(
    val available: Long? = null,
    val collectionURI: String? = null,
    val items: List<ComicsItem>? = null,
    val returned: Long? = null
)

data class Stories(
    val available: Long? = null,
    val collectionURI: String? = null,
    val items: List<StoriesItem>? = null,
    val returned: Long? = null
)

data class URL(
    val type: String? = null,
    val url: String? = null
)

data class ComicsItem(
    val resourceURI: String? = null,
    val name: String? = null
)

data class StoriesItem(
    val resourceURI: String? = null,
    val name: String? = null,
    val type: String? = null
)

data class Thumbnail(
    val path: String? = null,
    val extension: String? = null
)
