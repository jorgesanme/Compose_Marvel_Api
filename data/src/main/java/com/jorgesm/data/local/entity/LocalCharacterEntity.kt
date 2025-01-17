package com.jorgesm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocalCharacter")
data class LocalCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String?,
    val description: String?,
    var thumbnail: String,
    var nickName: String?,
    var isFavorite: Boolean,
    val comics: Int? = 0,
    val series: Int? = 0,
    val stories: Int? = 0,
    val events: Int? = 0,
)