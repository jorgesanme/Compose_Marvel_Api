package com.jorgesm.compose_marvel_api.utils

sealed class Routes(val route: String) {
    object CharactersListScreen:Routes("charactersList")
    object DetailScreen: Routes("detail/{itemId}"){
        fun addItemId(itemId: Long)= "detail/$itemId"
    }
}