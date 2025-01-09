package com.jorgesm.compose_marvel_api.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class DetailScreen(val itemId: Long)