package com.jorgesm.domain.model


data class Character(
    val id: Long,
    val name: String,
    val description: String,
    var thumbnail: String,
    var nickName: String,
    var isFavorite: Boolean,
    val comics: Int? = 0,
    val series: Int? = 0,
    val stories: Int? = 0,
    val events: Int? = 0,
)
