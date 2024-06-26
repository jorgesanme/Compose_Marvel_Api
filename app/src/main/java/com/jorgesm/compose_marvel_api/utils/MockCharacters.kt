package com.jorgesm.compose_marvel_api.utils

import com.jorgesm.domain.model.Character

fun getEmptyCharacterDetails(): Character {
    return Character(
        id = 0,
        name = "",
        description = "",
        thumbnail = "",
        nickName = "",
        isFavorite = false,
        comics = 9,
        series = 16,
        stories = 10,
        events = 26,
    )
}
