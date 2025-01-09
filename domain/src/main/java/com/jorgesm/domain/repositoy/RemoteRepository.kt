package com.jorgesm.domain.repositoy

interface RemoteRepository {
    suspend fun getAllCharacters(offset: String): com.jorgesm.domain.model.response.CharactersResponse

    suspend fun getCharacterById(itemId: String): com.jorgesm.domain.model.response.CharactersResponse
}