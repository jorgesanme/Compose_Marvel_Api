package com.jorgesm.domain.repository

import com.jorgesm.domain.model.Character
import com.jorgesm.domain.model.response.CharactersResponse
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAllCharacters(): Flow<CharactersResponse>
    suspend fun saveAllCharacters(characters: List<Character>)

    suspend fun findCharacterById(item: Long): Character

    suspend fun updateCharacter(item: Character)

}