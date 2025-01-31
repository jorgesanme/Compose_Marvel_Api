package com.jorgesm.data.mappers


import com.jorgesm.data.local.entity.LocalCharacterEntity
import com.jorgesm.data.server.models.Result
import com.jorgesm.data.utils.Const
import com.jorgesm.domain.model.Character


fun Result.transformToDomain() = Character(
    id = this.id,
    name = this.name ?: "",
    description = this.description ?: "",
    thumbnail = this.thumbnail?.path?.replace(
        "http",
        "https"
    ) + "${Const.IMAGE_SITE}." + this.thumbnail?.extension,
    nickName = "",
    isFavorite = false,
    comics = this.comics?.available?.toInt(),
    series = this.series?.available?.toInt(),
    stories = this.stories?.available?.toInt(),
    events = this.stories?.available?.toInt(),
)

fun List<Result>.transformToDomain(): List<Character> =
    this.map { it.transformToDomain() }


fun LocalCharacterEntity.transformFromDDBB() = Character(
    id = this.id,
    name = this.name ?: "",
    description = this.description ?: "",
    thumbnail = this.thumbnail,
    nickName = this.nickName ?: "",
    isFavorite = this.isFavorite,
    comics = this.comics ?: 0,
    series = this.series ?: 0,
    stories = this.stories ?: 0,
    events = this.events ?: 0,
)

fun List<LocalCharacterEntity>.transformFromDDBB(): List<Character> {
    val listCharacter: MutableList<Character> = mutableListOf()
    this.map { listCharacter.add(it.transformFromDDBB()) }
    return listCharacter
}


fun Character.transformToDDBB() =
    LocalCharacterEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = this.thumbnail,
        nickName = this.nickName,
        isFavorite = this.isFavorite,
        comics = this.comics,
        series = this.series,
        stories = this.stories,
        events = this.events
    )



