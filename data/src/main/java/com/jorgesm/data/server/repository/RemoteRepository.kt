package com.jorgesm.data.server.repository

import com.jorgesm.domain.model.response.CharactersResponse

interface RemoteRepository {
    suspend fun getAllCharacters(): CharactersResponse

    suspend fun getCharacterById(itemId: String): CharactersResponse
}