package com.jorgesm.domain.repositoy

import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllCharacters(): Flow<com.jorgesm.domain.model.response.CharactersResponse>
    suspend fun saveAllCharacters(characters: List<com.jorgesm.domain.model.Character>)

    suspend fun findCharacterById(item: Long): com.jorgesm.domain.model.Character

    suspend fun updateCharacter(item: com.jorgesm.domain.model.Character)

}