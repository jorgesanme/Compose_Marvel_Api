package com.jorgesm.data.mappers


import com.jorgesm.data.models.Result
import com.jorgesm.data.models.Thumbnail
import com.jorgesm.data.utils.Const
import com.jorgesm.domain.model.Character


fun Result.transformToDomain() =  Character(
        id = this.id?.toInt(),
        name = this.name ,
        description = this.description ,
        thumbnail =  this.thumbnail?.path?.replace("http","https") + "${Const.IMAGE_SITE}."+ this.thumbnail?.extension
    )

fun List<Result>.transformToDomain(): List<Character> =
    this.map { it.transformToDomain() }




