package com.jorgesm.data.server.repository

import com.jorgesm.domain.model.response.CharactersResponse

interface RemoteRepository {
    suspend fun getAllCharacters(offset: String): CharactersResponse

    suspend fun getCharacterById(itemId: String): CharactersResponse
}