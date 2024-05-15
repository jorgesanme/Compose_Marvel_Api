package com.jorgesm.compose_marvel_api.presentation.extensions

import com.jorgesm.domain.model.Character


fun Character.getImageUrl(): String{
    return this.thumbnail
}