package com.jorgesm.domain.model


data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    var thumbnail: String,
    val comics: Int? = 0,
    val series: Int? = 0,
    val stories: Int? = 0,
    val events: Int? = 0,
)
